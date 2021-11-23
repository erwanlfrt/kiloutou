package controller.stats;

import model.dao.ComputerAccessoryDAO;
import model.dao.ComputerDAO;
import model.dao.EquipmentDAO;
import model.dao.VehicleAccessoryDAO;
import model.dao.VehicleDAO;

import model.object.equipment.Equipment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DBManager;

public class StatisticController extends HttpServlet {
	private Connection connection;
	private EquipmentDAO eDAO;
	private VehicleDAO vehicleDAO;
	private VehicleAccessoryDAO vehicleAccessoryDAO;
	private ComputerDAO computerDAO;
	private ComputerAccessoryDAO computerAccessoryDAO;

	public StatisticController() {
		super();

		this.connection = DBManager.getInstance().getConnection();

		this.eDAO = new EquipmentDAO();
		this.vehicleDAO = new VehicleDAO();
		this.vehicleAccessoryDAO = new VehicleAccessoryDAO();
		this.computerDAO = new ComputerDAO();
		this.computerAccessoryDAO = new ComputerAccessoryDAO();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/stats/statistic.jsp");

		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HashMap<String, Integer> equipmentDistribution = this.getEquipmentDistribution();
		HashMap<String, Integer> mostLoanedEquipments = this.getMostLoanedEquipments();
		HashMap<String, Integer> bestLoaners = this.getBestLoaner();
		HashMap<String, Integer> loanDistribution = this.getLoanDistribution();
		int[] loanDistributionPerMonth = this.getLoanDistributionPerMonth();

		req.setAttribute("equipmentDistribution", equipmentDistribution);
		req.setAttribute("mostLoanedEquipments", mostLoanedEquipments);
		req.setAttribute("bestLoaners", bestLoaners);
		req.setAttribute("loanDistribution", loanDistribution);
		req.setAttribute("loanDistributionPerMonth", loanDistributionPerMonth);

		this.doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	private HashMap<String, Integer> getEquipmentDistribution() {
		ArrayList<Equipment> allEquipments = this.eDAO.listAllIdAndName();
		ArrayList<Equipment> listVehicle = this.castArrayList(this.vehicleDAO.listAllIdAndName());
		ArrayList<Equipment> listVehicleAccessory = this.castArrayList(this.vehicleAccessoryDAO.listAllIdAndName());
		ArrayList<Equipment> listComputer = this.castArrayList(this.computerDAO.listAllIdAndName());
		ArrayList<Equipment> listComputerAccessory = this.castArrayList(this.computerAccessoryDAO.listAllIdAndName());
		ArrayList<Equipment> other = new ArrayList<Equipment>();

		for (Equipment equipment : allEquipments) {
			boolean isAVehicle = false;
			boolean isAVehicleAccessory = false;
			boolean isAComputerAccessory = false;
			boolean isAComputer = false;
			for (Equipment vehicle : listVehicle) {
				if (vehicle.getId() == equipment.getId()) {
					isAVehicle = true;
					break;
				}
			}
			for (Equipment va : listVehicleAccessory) {
				if (va.getId() == equipment.getId()) {
					isAVehicleAccessory = true;
					break;
				}
			}
			for (Equipment computer : listComputer) {
				if (computer.getId() == equipment.getId()) {
					isAComputer = true;
					break;
				}
			}

			for (Equipment computerAccessory : listComputerAccessory) {
				if (computerAccessory.getId() == equipment.getId()) {
					isAComputerAccessory = true;
					break;
				}
			}

			if (!isAComputer && !isAVehicle && !isAComputerAccessory && !isAVehicleAccessory) {
				other.add(equipment);
			}

		}
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		result.put("Véhicules", listVehicle.size());
		result.put("Ordinateurs", listComputer.size());
		result.put("Accessoires automobiles", listVehicleAccessory.size());
		result.put("Accessoires informatiques", listComputerAccessory.size());
		result.put("Autre", other.size());

		return result;
	}

	private HashMap<String, Integer> getMostLoanedEquipments() {
		HashMap<String, Integer> results = new HashMap<String, Integer>();
		PreparedStatement statement;
		try {
			statement = this.connection.prepareStatement(
					"SELECT Equipment.id,  COUNT(Equipment.id) AS count_id, name FROM Loan,Equipment WHERE Equipment.id=Loan.equipmentId GROUP BY Equipment.id ORDER BY count(Equipment.id) DESC ;");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				results.put(rs.getString("name"), rs.getInt("count_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return results;
	}

	private HashMap<String, Integer> getBestLoaner() {
		HashMap<String, Integer> results = new HashMap<String, Integer>();
		PreparedStatement statement;
		try {
			statement = this.connection.prepareStatement(
					"SELECT name, firstname, count(User.mail) AS count_loan FROM Loan, User WHERE Loan.userMail = User.mail GROUP BY User.mail ORDER BY count(User.mail) DESC;");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				results.put((rs.getString("firstname") + rs.getString("name")), rs.getInt("count_loan"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return results;
	}

	private HashMap<String, Integer> getLoanDistribution() {
		HashMap<String, Integer> results = new HashMap<String, Integer>();
		String loansToCome = "SELECT COUNT(*) AS loans_to_come FROM Loan WHERE beginningDate > CURDATE();";
		String current = " SELECT COUNT(*) AS current FROM Loan WHERE beginningDate < CURDATE() AND isOver=0;";
		String done = "SELECT COUNT(*) AS done FROM Loan WHERE endDate < CURDATE() AND isOver=1;";

		PreparedStatement statement;
		try {
			statement = this.connection.prepareStatement(loansToCome);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				results.put("Emprunts à venir", rs.getInt("loans_to_come"));
			}
			statement = this.connection.prepareStatement(current);
			rs = statement.executeQuery();
			while (rs.next()) {
				results.put("Emprunts en cours", rs.getInt("current"));
			}

			statement = this.connection.prepareStatement(done);
			rs = statement.executeQuery();
			while (rs.next()) {
				results.put("Emprunts terminés", rs.getInt("done"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return results;
	}

	private int[] getLoanDistributionPerMonth() {
		int[] results = new int[12];
		PreparedStatement statement;
		ResultSet rs;
		LocalDate beginning;
		LocalDate end;
		for (int i = 2; i <= 13; i++) {
			beginning = LocalDate.of(LocalDate.now().getYear(), i - 1, 1);
			if (i == 13) {
				end = LocalDate.of(LocalDate.now().getYear() + 1, 1, 1);
			} else {
				end = LocalDate.of(LocalDate.now().getYear(), i, 1);
			}
			try {
				statement = this.connection
						.prepareStatement("SELECT COUNT(*) AS count_month FROM Loan WHERE beginningDate < DATE(' "
								+ end.toString() + "') AND beginningDate >= DATE('" + beginning.toString() + "');");
				rs = statement.executeQuery();
				while (rs.next()) {
					results[i - 2] = rs.getInt("count_month");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return results;
	}

	private <N, O> ArrayList<N> castArrayList(ArrayList<O> list) {
		ArrayList<N> newlyCastedArrayList = new ArrayList<N>();
		for (O listObject : list) {
			newlyCastedArrayList.add((N) listObject);
		}
		return newlyCastedArrayList;
	}

}
