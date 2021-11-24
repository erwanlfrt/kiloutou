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

public class GetOtherController extends HttpServlet {
	private Connection connection;
	private Gson gson;
	private EquipmentDAO equipmentDAO;

	public GetOtherController() {
		this.connection = DBManager.getInstance().getConnection();
		this.equipmentDAO = new EquipmentDAO();
		this.gson = new Gson();
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Equipment equipment = this.equipmentDAO.get(Integer.parseInt(req.getParameter("id")));
		// this.equipmentDAO.closeConn();
		
		// add already loaned periods
		PreparedStatement dateStatement;
		try {
			dateStatement = this.connection
					.prepareStatement("SELECT beginningDate, endDate FROM Loan WHERE equipmentId = ?");
			dateStatement.setInt(1, equipment.getId());
			ResultSet rsDates = dateStatement.executeQuery();

			while (rsDates.next()) {
				equipment.addPeriod(rsDates.getString("beginningDate"), rsDates.getString("endDate"));
			}
			rsDates.close();
			dateStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		String equipmentJson = this.gson.toJson(equipment);

		out.print(equipmentJson);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
