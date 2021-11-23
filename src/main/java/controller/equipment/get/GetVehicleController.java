package controller.equipment.get;

import model.dao.*;
import model.object.equipment.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import model.DBManager;

public class GetVehicleController extends HttpServlet {
	private Connection connection;
	private Gson gson;
	private VehicleDAO vehicleDAO;

	public GetVehicleController() {
		this.connection = DBManager.getInstance().getConnection();
		this.vehicleDAO = new VehicleDAO();
		this.gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Vehicle vehicle = this.vehicleDAO.get(Integer.parseInt(req.getParameter("id")));
		this.vehicleDAO.closeConn();

		// add already loaned periods
		PreparedStatement dateStatement;
		try {
			dateStatement = this.connection
					.prepareStatement("SELECT beginningDate, endDate FROM Loan WHERE equipmentId = ?");
			dateStatement.setInt(1, vehicle.getId());
			ResultSet rsDates = dateStatement.executeQuery();

			while (rsDates.next()) {
				vehicle.addPeriod(rsDates.getString("beginningDate"), rsDates.getString("endDate"));
			}
			rsDates.close();
			dateStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		String vehicleJson = this.gson.toJson(vehicle);

		out.print(vehicleJson);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
