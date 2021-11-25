package controller.equipment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.auth.Role;
import controller.router.Router;
import model.dao.BikeDAO;
import model.dao.CarDAO;
import model.dao.ComputerAccessoryDAO;
import model.dao.ComputerDAO;
import model.dao.EquipmentDAO;
import model.dao.GraphicCardDAO;
import model.dao.ProcessorDAO;
import model.dao.VehicleAccessoryDAO;
import model.dao.VehicleDAO;
import model.object.equipment.Bike;
import model.object.equipment.Car;
import model.object.equipment.Computer;
import model.object.equipment.ComputerAccessory;
import model.object.equipment.Equipment;
import model.object.equipment.GraphicCard;
import model.object.equipment.Processor;
import model.object.equipment.Vehicle;
import model.object.equipment.VehicleAccessory;
import model.object.user.Profil;

public class ModifyEquipmentController extends HttpServlet {
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String pageName = "/view/equipment/add-equipment.jsp";

		Router.forward(pageName, this, request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!Role.can(this, req, resp, Profil.EQUIPMENT_ADMIN, Profil.ADMIN))
			return;
		
		int id = Integer.parseInt(req.getParameter("id"));

		EquipmentDAO equipmentDAO = new EquipmentDAO();
		Equipment equipment = equipmentDAO.get(id);
		equipmentDAO.closeConn();

		ProcessorDAO processorDAO = new ProcessorDAO();
		ArrayList<Processor> processors = processorDAO.listAll();
		processorDAO.closeConn();

		GraphicCardDAO gcDAO = new GraphicCardDAO();
		ArrayList<GraphicCard> graphicCards = gcDAO.listAll();
		gcDAO.closeConn();

		req.setAttribute("processors", processors);
		req.setAttribute("graphicCards", graphicCards);

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
		if (!Role.can(this, req, resp, Profil.EQUIPMENT_ADMIN, Profil.ADMIN))
			return;
		
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		HashMap<String, Object> params = new HashMap<String, Object>();

		String category = req.getParameter("categories");

		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		boolean available = req.getParameter("available").equals("available");
		String imageURL = req.getParameter("imageURL");
		boolean canBeLoaned = req.getParameter("canBeLoaned").equals("true");

		params.put("name", name);
		params.put("available", available);
		params.put("imageURL", imageURL);
		params.put("canBeLoaned", canBeLoaned);

		Equipment equipment = new Equipment(id, name, available, imageURL, canBeLoaned);

		equipmentDAO.update(equipment, params);
		equipmentDAO.closeConn();

		String registrationNumber = req.getParameter("registrationNumber");
		String serialNumber = req.getParameter("serialNumber");

		// if vehicle is chosen
		if (registrationNumber != null) {
			int renewalKilometers = Integer.parseInt(req.getParameter("renewalKilometers"));
			int kilometers = Integer.parseInt(req.getParameter("kilometers"));
			String model = req.getParameter("model");
			String brand = req.getParameter("brand");
			String state = req.getParameter("state");
			int maxSpeed = Integer.parseInt(req.getParameter("maxSpeed"));
			int power = Integer.parseInt(req.getParameter("power"));
			int numberOfSpeeds = Integer.parseInt(req.getParameter("numberOfSpeeds"));

			params = new HashMap<String, Object>();
			params.put("renewalKilometers", renewalKilometers);
			params.put("kilometers", kilometers);
			params.put("model", model);
			params.put("brand", brand);
			params.put("state", state);
			params.put("maxSpeed", maxSpeed);
			params.put("power", power);
			params.put("numberOfSpeeds", numberOfSpeeds);

			Vehicle vehicle = new Vehicle(id, name, available, imageURL, canBeLoaned, kilometers, brand, state,
					maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers);
			VehicleDAO vehicleDAO = new VehicleDAO();
			vehicleDAO.update(vehicle, params);
			vehicleDAO.closeConn();

			String numberOfSeatsString = req.getParameter("numberOfSeats");
			String numberOfCylindersString = req.getParameter("numberOfCylinders");

			if (numberOfSeatsString != null) {
				int numberOfSeats = Integer.parseInt(numberOfSeatsString);
				params = new HashMap<String, Object>();
				params.put("numberOfSeats", numberOfSeats);

				Car car = new Car(id, name, available, imageURL, canBeLoaned, kilometers, brand, state, maxSpeed,
						numberOfSpeeds, model, power, registrationNumber, renewalKilometers, numberOfSeats);
				CarDAO carDAO = new CarDAO();
				carDAO.update(car, params);
				carDAO.closeConn();
			} else if (numberOfCylindersString != null) {
				int numberOfCylinders = Integer.parseInt(numberOfCylindersString);
				params = new HashMap<String, Object>();
				params.put("numberOfCylinders", numberOfCylinders);
				Bike bike = new Bike(id, name, available, imageURL, canBeLoaned, kilometers, brand, state, maxSpeed,
						numberOfSpeeds, model, power, registrationNumber, renewalKilometers, numberOfCylinders);
				BikeDAO bikeDAO = new BikeDAO();
				bikeDAO.update(bike, params);
				bikeDAO.closeConn();
			}

		}
		// else if computer is chosen
		else if (serialNumber != null) {
			ComputerDAO computerDAO = new ComputerDAO();
			ProcessorDAO processorDAO = new ProcessorDAO();
			GraphicCardDAO graphicCardDAO = new GraphicCardDAO();

			String[] processorChoice = req.getParameterValues("processorChoice");
			String[] gcChoice = req.getParameterValues("graphicCardChoice");

			Processor processor;
			GraphicCard graphicCard;
			if (processorChoice != null) {
				// add processor
				int processorId = processorDAO.autoIncrementId();
				String processorBrand = req.getParameter("processorBrand");
				String processorName = req.getParameter("processorName");
				int numberOfCores = Integer.parseInt(req.getParameter("numberOfCores"));
				float processorFrequency = Float.parseFloat(req.getParameter("processorFrequency"));
				processor = new Processor(processorId, processorName, processorBrand, numberOfCores,
						processorFrequency);
				processorDAO.add(processor);
				processorDAO.closeConn();
			} else {
				processor = new Processor(Integer.parseInt(req.getParameter("processorSelect")), "", "", 0, 0.0f);
			}

			if (gcChoice != null) {
				// add graphic card
				int graphicCardId = graphicCardDAO.autoIncrementId();
				String graphicCardBrand = req.getParameter("graphicCardBrand");
				String graphicCardName = req.getParameter("graphicCardName");
				float graphicCardFrequency = Float.parseFloat(req.getParameter("graphicCardFrequency"));
				graphicCard = new GraphicCard(graphicCardId, graphicCardName, graphicCardBrand, graphicCardFrequency);
				graphicCardDAO.add(graphicCard);
				graphicCardDAO.closeConn();
			} else {
				graphicCard = new GraphicCard(Integer.parseInt(req.getParameter("graphicCardSelect")), "", "", 0.0f);
			}

			// add computer
			String brand = req.getParameter("brand");
			String model = req.getParameter("model");
			int memorySize = Integer.parseInt(req.getParameter("memorySize"));
			boolean isLaptop = req.getParameter("isLaptop").equals("true");
			int screenSize = Integer.parseInt(req.getParameter("screenSize"));
			String date = req.getParameter("purchaseDate");
			String renewalDate = req.getParameter("renewalDate");

			params = new HashMap<String, Object>();
			params.put("brand", brand);
			params.put("model", model);
			params.put("memorySize", memorySize);
			params.put("isLaptop", isLaptop);
			params.put("screenSize", screenSize);
			params.put("purchaseDate", date);
			params.put("renewalDate", renewalDate);
			params.put("processorId", processor.getId());
			params.put("graphicCardId", graphicCard.getId());

			Computer computer = new Computer(id, name, available, imageURL, canBeLoaned, brand, model, serialNumber,
					memorySize, isLaptop, screenSize, date, renewalDate, processor, graphicCard);
			computerDAO.update(computer, params);
			computerDAO.closeConn();
		} else if (category.equals("computerAccessory")) {
			ComputerAccessory computerAccessory = new ComputerAccessory(id, name, available, imageURL, canBeLoaned);
			ComputerAccessoryDAO caDAO = new ComputerAccessoryDAO();
			params = new HashMap<String, Object>();
			caDAO.update(computerAccessory, params);
			caDAO.closeConn();
		} else if (category.equals("vehicleAccessory")) {
			VehicleAccessory vehicleAccessory = new VehicleAccessory(id, name, available, imageURL, canBeLoaned);
			VehicleAccessoryDAO vaDAO = new VehicleAccessoryDAO();
			params = new HashMap<String, Object>();
			vaDAO.update(vehicleAccessory, params);
			vaDAO.closeConn();
		}

		String pageName = "/welcome";

		Router.redirect(pageName, this, req, resp);
	}
}
