package controller.equipment.list;

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

public class ListComputerAccessoriesController extends HttpServlet {

	private Gson gson;
	private ComputerAccessoryDAO computerAccessoryDAO;

	public ListComputerAccessoriesController() {
		this.computerAccessoryDAO = new ComputerAccessoryDAO();
		this.gson = new Gson();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)  {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Equipment> listComputerAccessories = this.computerAccessoryDAO.listAllIdAndName();
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
    String computerAccessoriesJson = this.gson.toJson(listComputerAccessories);
    out.print(computerAccessoriesJson);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
