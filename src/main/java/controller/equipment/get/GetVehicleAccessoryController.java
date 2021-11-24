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

public class GetVehicleAccessoryController extends HttpServlet {
	private Connection connection;
	private Gson gson;
	private VehicleAccessoryDAO vehicleAccessoryDAO;

	public GetVehicleAccessoryController() {
		this.connection = DBManager.getInstance().getConnection();
		this.vehicleAccessoryDAO = new VehicleAccessoryDAO();
		this.gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VehicleAccessory vehicleAccessory = this.vehicleAccessoryDAO.get(Integer.parseInt(req.getParameter("id")));
		// this.vehicleAccessoryDAO.closeConn();
		
		// add already loaned periods
		PreparedStatement dateStatement;
		try {
			dateStatement = this.connection
					.prepareStatement("SELECT beginningDate, endDate FROM Loan WHERE equipmentId = ?");
			dateStatement.setInt(1, vehicleAccessory.getId());
			ResultSet rsDates = dateStatement.executeQuery();

			while (rsDates.next()) {
				vehicleAccessory.addPeriod(rsDates.getString("beginningDate"), rsDates.getString("endDate"));
			}
			rsDates.close();
			dateStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		String vehicleAccessoryJson = this.gson.toJson(vehicleAccessory);

		out.print(vehicleAccessoryJson);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
