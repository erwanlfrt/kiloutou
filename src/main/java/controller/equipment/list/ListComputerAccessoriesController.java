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

public class ListComputerAccessoriesController extends HttpServlet {

	private Gson gson;
	private ComputerAccessoryDAO computerAccessoryDAO;

	public ListComputerAccessoriesController() {
		this.computerAccessoryDAO = new ComputerAccessoryDAO();
		this.gson = new Gson();
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!Role.can(this, req, resp, Profil.EQUIPMENT_ADMIN, Profil.ADMIN))
			return;

		ArrayList<Equipment> listComputerAccessories = this.computerAccessoryDAO.listAllIdAndName();
		this.computerAccessoryDAO.closeConn();
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		String computerAccessoriesJson = this.gson.toJson(listComputerAccessories);
		out.print(computerAccessoriesJson);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!Role.can(this, req, resp, Profil.FORBIDDEN))
			return;
	}
}
