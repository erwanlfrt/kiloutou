package controller.equipment.get;


import model.dao.*;
import model.object.equipment.*;
import model.object.loan.Loan;
import model.object.user.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import model.DBManager;

public class GetComputerAccessoryController extends HttpServlet {
  private Connection connection;
	private Gson gson;
	private ComputerAccessoryDAO computerAccessoryDAO;

	public GetComputerAccessoryController() {
    this.connection = DBManager.getInstance().getConnection();
		this.computerAccessoryDAO = new ComputerAccessoryDAO();
		this.gson = new Gson();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)  {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ComputerAccessory computerAccessory = this.computerAccessoryDAO.get(Integer.parseInt(req.getParameter("id")));

    // add already loaned periods
    PreparedStatement dateStatement;
	try {
		dateStatement = this.connection.prepareStatement("SELECT beginningDate, endDate FROM Loan WHERE equipmentId = ?");
		dateStatement.setInt(1, computerAccessory.getId());
	    ResultSet rsDates = dateStatement.executeQuery();
	    
	    while(rsDates.next()) {
	      computerAccessory.addPeriod(rsDates.getString("beginningDate"), rsDates.getString("endDate"));
	    } 
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    


		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

    String computerAccessoryJson = this.gson.toJson(computerAccessory);

    out.print(computerAccessoryJson);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}