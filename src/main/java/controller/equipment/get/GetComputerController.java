package controller.equipment.get;

import model.dao.*;
import model.object.equipment.*;
import model.object.user.Profil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controller.auth.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import model.DBManager;

public class GetComputerController extends HttpServlet {
	private Connection connection;
	private Gson gson;
	private ComputerDAO computerDAO;

	public GetComputerController() {
		this.connection = DBManager.getInstance().getConnection();
		this.computerDAO = new ComputerDAO();
		this.gson = new Gson();
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!Role.can(this, req, resp, Profil.EQUIPMENT_ADMIN, Profil.ADMIN))
			return;
		
		Computer computer = this.computerDAO.get(Integer.parseInt(req.getParameter("id")));
		// this.computerDAO.closeConn();

		// add already loaned periods
		PreparedStatement dateStatement;
		try {
			dateStatement = this.connection
					.prepareStatement("SELECT beginningDate, endDate FROM Loan WHERE equipmentId = ?");
			dateStatement.setInt(1, computer.getId());
			ResultSet rsDates = dateStatement.executeQuery();
			while (rsDates.next()) {
				computer.addPeriod(rsDates.getString("beginningDate"), rsDates.getString("endDate"));
			}
			rsDates.close();
			dateStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		String computerJson = this.gson.toJson(computer);

		out.print(computerJson);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!Role.can(this, req, resp, Profil.FORBIDDEN))
			return;
	}

}
