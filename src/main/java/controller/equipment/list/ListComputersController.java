package controller.equipment.list;

import model.dao.*;
import model.object.equipment.*;
import model.object.user.Profil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controller.auth.Role;

public class ListComputersController extends HttpServlet {

	private Gson gson;
	private ComputerDAO computerDAO;

	public ListComputersController() {
		this.computerDAO = new ComputerDAO();
		this.gson = new Gson();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!Role.can(this, req, resp, Profil.EQUIPMENT_ADMIN, Profil.ADMIN))
			return;
		
		ArrayList<Equipment> listComputers = this.computerDAO.listAllIdAndName();
		this.computerDAO.closeConn();
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		String computersJson = this.gson.toJson(listComputers);
		out.print(computersJson);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!Role.can(this, req, resp, Profil.FORBIDDEN))
			return;
	}
}
