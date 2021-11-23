package controller.equipment;

import model.dao.EquipmentDAO;
import model.object.equipment.Equipment;

import model.dao.VehicleDAO;
import model.object.equipment.Vehicle;

import model.dao.BikeDAO;
import model.object.equipment.Bike;

import model.dao.CarDAO;
import model.object.equipment.Car;

import model.dao.ComputerDAO;
import model.object.equipment.Computer;

import model.dao.ComputerAccessoryDAO;
import model.object.equipment.ComputerAccessory;

import model.dao.VehicleAccessoryDAO;
import model.object.equipment.VehicleAccessory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchEquipmentController extends HttpServlet {
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String pageName = "/view/equipment/search-equipment.jsp";

		RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		VehicleDAO vehicleDAO = new VehicleDAO();
		BikeDAO bikeDAO = new BikeDAO();
		CarDAO carDAO = new CarDAO();
		VehicleAccessoryDAO vehicleAccessoryDAO = new VehicleAccessoryDAO();
		ComputerDAO computerDAO = new ComputerDAO();
		ComputerAccessoryDAO computerAccessoryDAO = new ComputerAccessoryDAO();

		HashMap<String, ArrayList<Equipment>> lists = new HashMap<String, ArrayList<Equipment>>();

		// list bikes
		ArrayList<Bike> bikes = bikeDAO.listAll();
		for (Bike b : bikes) {
			ids.add(b.getId());
		}
		ArrayList<Equipment> suitableBikes = this.castArrayList(bikes);
		lists.put("Motos", suitableBikes);

		// list cars
		ArrayList<Car> cars = carDAO.listAll();
		for (Car c : cars) {
			ids.add(c.getId());
		}
		ArrayList<Equipment> suitableCars = this.castArrayList(cars);
		lists.put("Voitures", suitableCars);

		ArrayList<Vehicle> vehicles = vehicleDAO.listAll();
		ArrayList<Vehicle> vToRemove = new ArrayList<Vehicle>();
		for (Vehicle v : vehicles) {
			if (!ids.contains(v.getId())) {
				ids.add(v.getId());
			} else {
				vToRemove.add(v);
			}
		}
		for (Vehicle v : vToRemove) {
			vehicles.remove(v);
		}
		ArrayList<Equipment> suitableVehicles = this.castArrayList(vehicles);
		lists.put("Véhicules", suitableVehicles);

		ArrayList<VehicleAccessory> vehicleAccessories = vehicleAccessoryDAO.listAll();
		for (VehicleAccessory va : vehicleAccessories) {
			ids.add(va.getId());
		}
		ArrayList<Equipment> suitableVA = this.castArrayList(vehicleAccessories);
		lists.put("Accessoires pour véhicule", suitableVA);

		ArrayList<Computer> computers = computerDAO.listAll();
		for (Computer c : computers) {
			ids.add(c.getId());
		}
		ArrayList<Equipment> suitableComputers = this.castArrayList(computers);
		lists.put("Ordinateurs", suitableComputers);

		ArrayList<ComputerAccessory> computerAccessories = computerAccessoryDAO.listAll();
		for (ComputerAccessory ca : computerAccessories) {
			ids.add(ca.getId());
		}
		ArrayList<Equipment> suitableCA = this.castArrayList(computerAccessories);
		lists.put("Accessoires informatiques", suitableCA);

		ArrayList<Equipment> equipments = equipmentDAO.listAll();
		ArrayList<Equipment> eToRemove = new ArrayList<Equipment>();
		for (Equipment e : equipments) {
			if (ids.contains(e.getId())) {
				eToRemove.add(e);
			}
		}
		for (Equipment e : eToRemove) {
			equipments.remove(e);
		}
		lists.put("Autre", equipments);

		req.setAttribute("lists", lists);
		
		equipmentDAO.closeConn();
		vehicleDAO.closeConn();
		bikeDAO.closeConn();
		carDAO.closeConn();
		vehicleAccessoryDAO.closeConn();
		computerDAO.closeConn();
		computerAccessoryDAO.closeConn();

		this.doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doProcess(req, resp);
	}

	private <N, O> ArrayList<N> castArrayList(ArrayList<O> list) {
		ArrayList<N> newlyCastedArrayList = new ArrayList<N>();
		for (O listObject : list) {
			newlyCastedArrayList.add((N) listObject);
		}
		return newlyCastedArrayList;
	}
}
