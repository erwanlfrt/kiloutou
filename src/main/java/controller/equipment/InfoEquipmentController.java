package controller.equipment;

import model.dao.BikeDAO;
import model.dao.CarDAO;
import model.dao.ComputerAccessoryDAO;
import model.dao.ComputerDAO;
import model.dao.EquipmentDAO;
import model.dao.VehicleAccessoryDAO;
import model.dao.VehicleDAO;
import model.object.equipment.Bike;
import model.object.equipment.Car;
import model.object.equipment.Computer;
import model.object.equipment.ComputerAccessory;
import model.object.equipment.Equipment;
import model.object.equipment.Vehicle;
import model.object.equipment.VehicleAccessory;

import java.io.IOException;

import controller.router.Router;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InfoEquipmentController extends HttpServlet {
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String pageName = "/view/equipment/info-equipment.jsp";

		Router.forward(pageName, this, request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		EquipmentDAO equipmentDAO = new EquipmentDAO();
		Equipment equipment = equipmentDAO.get(id);
		equipmentDAO.closeConn();

		if (equipment != null) {
			req.setAttribute("equipment", equipment);

			// check if equipment is a vehicle
			VehicleDAO vehicleDAO = new VehicleDAO();
			Vehicle vehicle = vehicleDAO.get(id);
			vehicleDAO.closeConn();

			if (vehicle != null) {
				req.setAttribute("vehicle", vehicle);

				// check if equipment is a car
				CarDAO carDAO = new CarDAO();
				Car car = carDAO.get(id);
				carDAO.closeConn();

				if (car != null) {
					req.setAttribute("car", car);
				} else {
					// check if equipment is a bike
					BikeDAO bikeDAO = new BikeDAO();
					Bike bike = bikeDAO.get(id);
					bikeDAO.closeConn();

					if (bike != null) {
						req.setAttribute("bike", bike);
					}
				}
			} else {
				// check if equipment is a computer
				ComputerDAO computerDAO = new ComputerDAO();
				Computer computer = computerDAO.get(id);
				computerDAO.closeConn();

				if (computer != null) {
					req.setAttribute("computer", computer);
				} else {
					// check if equipment is a computer accessory
					ComputerAccessoryDAO computerAccessoryDAO = new ComputerAccessoryDAO();
					ComputerAccessory computerAccessory = computerAccessoryDAO.get(id);
					computerAccessoryDAO.closeConn();

					if (computerAccessory != null) {
						req.setAttribute("computerAccessory", computerAccessory);
					} else {
						// check if equipment is a vheicle accessory
						VehicleAccessoryDAO vehicleAccessoryDAO = new VehicleAccessoryDAO();
						VehicleAccessory vehicleAccessory = vehicleAccessoryDAO.get(id);
						vehicleAccessoryDAO.closeConn();

						if (vehicleAccessory != null) {
							req.setAttribute("vehicleAccessory", vehicleAccessory);
						}
					}
				}
			}
      this.doProcess(req, resp);
		} else {
			String pageName = "/error";
			Router.redirect(pageName, this, req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
