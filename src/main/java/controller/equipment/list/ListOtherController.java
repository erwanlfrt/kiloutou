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

public class ListOtherController extends HttpServlet {

	private Gson gson;

	private EquipmentDAO eDAO;
	private VehicleDAO vehicleDAO;
	private VehicleAccessoryDAO vehicleAccessoryDAO;
	private ComputerDAO computerDAO;
	private ComputerAccessoryDAO computerAccessoryDAO;

	public ListOtherController() {
		this.eDAO = new EquipmentDAO();
		this.vehicleDAO = new VehicleDAO();
		this.vehicleAccessoryDAO = new VehicleAccessoryDAO();
		this.computerDAO = new ComputerDAO();
		this.computerAccessoryDAO = new ComputerAccessoryDAO();
		this.gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!Role.can(this, req, resp, Profil.EQUIPMENT_ADMIN, Profil.ADMIN))
			return;
		
		ArrayList<Equipment> allEquipments = this.eDAO.listAllIdAndName();
		ArrayList<Equipment> listVehicle = this.castArrayList(this.vehicleDAO.listAllIdAndName());
		ArrayList<Equipment> listVehicleAccessory = this.castArrayList(this.vehicleAccessoryDAO.listAllIdAndName());
		ArrayList<Equipment> listComputer = this.castArrayList(this.computerDAO.listAllIdAndName());
		ArrayList<Equipment> listComputerAccessory = this.castArrayList(this.computerAccessoryDAO.listAllIdAndName());
		ArrayList<Equipment> other = new ArrayList<Equipment>();

		// list other equipments, TO IMPROVE
		for (Equipment equipment : allEquipments) {
			boolean isAVehicle = false;
			boolean isAVehicleAccessory = false;
			boolean isAComputerAccessory = false;
			boolean isAComputer = false;
			for (Equipment vehicle : listVehicle) {
				if (vehicle.getId() == equipment.getId()) {
					isAVehicle = true;
					break;
				}
			}
			for (Equipment va : listVehicleAccessory) {
				if (va.getId() == equipment.getId()) {
					isAVehicleAccessory = true;
					break;
				}
			}
			for (Equipment computer : listComputer) {
				if (computer.getId() == equipment.getId()) {
					isAComputer = true;
					break;
				}
			}

			for (Equipment computerAccessory : listComputerAccessory) {
				if (computerAccessory.getId() == equipment.getId()) {
					isAComputerAccessory = true;
					break;
				}
			}

			if (!isAComputer && !isAVehicle && !isAComputerAccessory && !isAVehicleAccessory) {
				other.add(equipment);
			}

		}
		
		
		this.eDAO.closeConn();
		this.vehicleDAO.closeConn();
		this.vehicleAccessoryDAO.closeConn();
		this.computerDAO.closeConn();
		this.computerAccessoryDAO.closeConn();

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		String otherJson = this.gson.toJson(other);
		out.print(otherJson);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!Role.can(this, req, resp, Profil.FORBIDDEN))
			return;
	}

	private <N, O> ArrayList<N> castArrayList(ArrayList<O> list) {
		ArrayList<N> newlyCastedArrayList = new ArrayList<N>();
		for (O listObject : list) {
			newlyCastedArrayList.add((N) listObject);
		}
		return newlyCastedArrayList;
	}
}
