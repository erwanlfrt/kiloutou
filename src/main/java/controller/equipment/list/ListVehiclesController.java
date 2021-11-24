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

public class ListVehiclesController extends HttpServlet {

	private Gson gson;
	private VehicleDAO vehicleDAO;

	public ListVehiclesController() {
		this.vehicleDAO = new VehicleDAO();
		this.gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!Role.can(this, req, resp, Profil.EQUIPMENT_ADMIN, Profil.ADMIN))
			return;
		
		ArrayList<Equipment> listVehicle = this.vehicleDAO.listAllIdAndName();
		this.vehicleDAO.closeConn();

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		String vehiclesJson = this.gson.toJson(listVehicle);

		out.print(vehiclesJson);
	}

}
