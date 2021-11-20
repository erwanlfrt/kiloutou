package controller.equipment.list;

import model.dao.*;
import model.object.equipment.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ListComputerAccessoriesController extends HttpServlet {

	private Gson gson;
	private ComputerAccessoryDAO computerAccessoryDAO;

	public ListComputerAccessoriesController() {
		this.computerAccessoryDAO = new ComputerAccessoryDAO();
		this.gson = new Gson();
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
