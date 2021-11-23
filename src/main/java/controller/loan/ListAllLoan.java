package controller.loan;

import model.dao.*;
import model.object.equipment.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ListAllLoan extends HttpServlet {

	private Gson gson;

	private EquipmentDAO eDAO;
	private VehicleDAO vehicleDAO;
	private VehicleAccessoryDAO vehicleAccessoryDAO;
	private ComputerDAO computerDAO;
	private ComputerAccessoryDAO computerAccessoryDAO;

	public ListAllLoan() {
		this.eDAO = new EquipmentDAO();
		this.vehicleDAO = new VehicleDAO();
		this.vehicleAccessoryDAO = new VehicleAccessoryDAO();
		this.computerDAO = new ComputerDAO();
		this.computerAccessoryDAO = new ComputerAccessoryDAO();
		this.gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HashMap<String, ArrayList<Equipment>> listEquipments = new HashMap<String, ArrayList<Equipment>>();

		ArrayList<Equipment> allEquipments = this.eDAO.listAll();
		ArrayList<Equipment> listVehicle = this.castArrayList(this.vehicleDAO.listAll());
		ArrayList<Equipment> listVehicleAccessory = this.castArrayList(this.vehicleAccessoryDAO.listAll());
		ArrayList<Equipment> listComputer = this.castArrayList(this.computerDAO.listAll());
		ArrayList<Equipment> listComputerAccessory = this.castArrayList(this.computerAccessoryDAO.listAll());
		ArrayList<Equipment> other = new ArrayList<Equipment>();
		
		this.eDAO.closeConn();
		this.vehicleDAO.closeConn();
		this.vehicleAccessoryDAO.closeConn();
		this.computerDAO.closeConn();
		this.computerAccessoryDAO.closeConn();

		// list other equipments, TO IMPROVE
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

		listEquipments.put("other", other);
		listEquipments.put("vehicle", listVehicle);
		listEquipments.put("vehicleAccessory", listVehicleAccessory);
		listEquipments.put("computer", listComputer);
		listEquipments.put("computerAccessory", listComputerAccessory);

		// ArrayList<Equipment> allEquipments = this.eDAO.listAll();
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		String otherJson = this.gson.toJson(other);
		String vehiclesJson = this.gson.toJson(listVehicle);
		String vehicleAccessoryJson = this.gson.toJson(listVehicleAccessory);
		String computersJson = this.gson.toJson(listComputer);
		String computerAccessoryJson = this.gson.toJson(listComputerAccessory);

		JsonObject object = new JsonObject();
		object.addProperty("other", otherJson);
		object.addProperty("vehicles", vehiclesJson);
		object.addProperty("vehicleAccessories", vehicleAccessoryJson);
		object.addProperty("computers", computersJson);
		object.addProperty("computerAccessories", computerAccessoryJson);
		// out.print(object);
		out.print(otherJson);
		out.print(vehiclesJson);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	private <N, O> ArrayList<N> castArrayList(ArrayList<O> list) {
		ArrayList<N> newlyCastedArrayList = new ArrayList<N>();
		for (O listObject : list) {
			newlyCastedArrayList.add((N) listObject);
		}
		return newlyCastedArrayList;
	}
}
