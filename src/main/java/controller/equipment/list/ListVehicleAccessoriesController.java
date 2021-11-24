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

public class ListVehicleAccessoriesController extends HttpServlet {

	private Gson gson;
	private VehicleAccessoryDAO vehicleAccessoryDAO;

	public ListVehicleAccessoriesController() {
		this.vehicleAccessoryDAO = new VehicleAccessoryDAO();
		this.gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (!Role.can(this, req, resp, Profil.EQUIPMENT_ADMIN, Profil.ADMIN))
			return;

		ArrayList<Equipment> listVehicleAccessories = this.vehicleAccessoryDAO.listAllIdAndName();
		this.vehicleAccessoryDAO.closeConn();
		
		// ArrayList<Equipment> allEquipments = this.eDAO.listAll();
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		String vehicleAccessoriesJson = this.gson.toJson(listVehicleAccessories);

		out.print(vehicleAccessoriesJson);
	}

}
