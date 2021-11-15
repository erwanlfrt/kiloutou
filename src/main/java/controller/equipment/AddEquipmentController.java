package controller.equipment;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddEquipmentController extends HttpServlet {
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String pageName="/view/equipment/add-equipment.jsp";
	
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
		this.doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    EquipmentDAO equipmentDAO = new EquipmentDAO();

    String category = req.getParameter("categories");

    int id = equipmentDAO.autoIncrementId();
		String name = req.getParameter("name");
		boolean available = req.getParameter("available").equals("available");
    String imageURL = req.getParameter("imageURL");
    boolean canBeLoaned = true;

    
    Equipment equipment = new Equipment(id, name, available, imageURL, canBeLoaned);

    equipmentDAO.add(equipment);

    String pageName="/welcome";
	
		RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

   
    //if vehicle is chosen
    String registrationNumber = req.getParameter("registrationNumber");
    String serialNumber = req.getParameter("serialNumber");
    if(registrationNumber != null) {
      int renewalKilometers = Integer.parseInt(req.getParameter("renewalKilometers"));
      int kilometers = Integer.parseInt(req.getParameter("kilometers"));
      String model = req.getParameter("model");
      String brand = req.getParameter("brand");
      String state = req.getParameter("state");
      int maxSpeed = Integer.parseInt(req.getParameter("maxSpeed"));
      int power = Integer.parseInt(req.getParameter("power"));
      int numberOfSpeeds = Integer.parseInt(req.getParameter("numberOfSpeeds"));

      Vehicle vehicle = new Vehicle(id, name, available, imageURL, canBeLoaned, kilometers, brand, state, maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers);
      VehicleDAO vehicleDAO = new VehicleDAO();
      vehicleDAO.add(vehicle);
      
      String numberOfSeatsString = req.getParameter("numberOfSeats");
      String numberOfCylindersString = req.getParameter("numberOfCylinders");
      
      if(numberOfSeatsString != null) {
    	  int numberOfSeats = Integer.parseInt(numberOfSeatsString);
    	  Car car = new Car(id, name, available, imageURL, canBeLoaned, kilometers, brand, state, maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers, numberOfSeats);
    	  CarDAO carDAO = new CarDAO();
    	  carDAO.add(car);
      }
      else if(numberOfCylindersString != null) {
    	  int numberOfCylinders = Integer.parseInt(numberOfCylindersString);
    	  Bike bike = new Bike(id, name, available, imageURL, canBeLoaned, kilometers, brand, state, maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers, numberOfCylinders);
    	  BikeDAO bikeDAO = new BikeDAO();
    	  bikeDAO.add(bike);
      }
      
    }
    //else if computer is chosen
    else if(serialNumber != null) {
      ComputerDAO computerDAO = new ComputerDAO();
      ProcessorDAO processorDAO = new ProcessorDAO();
      GraphicCardDAO graphicCardDAO = new GraphicCardDAO();

      // add processor
      int processorId = processorDAO.autoIncrementId();
      String processorBrand = req.getParameter("processorBrand");
      String processorName = req.getParameter("processorName");
      int numberOfCores = Integer.parseInt(req.getParameter("numberOfCores"));
      float processorFrequency = Float.parseFloat(req.getParameter("processorFrequency"));
      Processor processor = new Processor(processorId, processorName, processorBrand, numberOfCores, processorFrequency);
      processorDAO.add(processor);

      // add graphic card
      int graphicCardId = graphicCardDAO.autoIncrementId();
      String graphicCardBrand = req.getParameter("graphicCardBrand");
      String graphicCardName = req.getParameter("graphicCardName");
      float graphicCardFrequency = Float.parseFloat(req.getParameter("graphicCardFrequency"));
      GraphicCard graphicCard = new GraphicCard(graphicCardId, graphicCardName, graphicCardBrand, graphicCardFrequency);
      graphicCardDAO.add(graphicCard);

      // add computer
      String brand = req.getParameter("brand");
      String model = req.getParameter("model");
      int memorySize = Integer.parseInt(req.getParameter("memorySize"));
      boolean isLaptop = req.getParameter("isLaptop").equals("true");
      int screenSize = Integer.parseInt(req.getParameter("screenSize"));
      String date = req.getParameter("purchaseDate");
      String renewalDate = req.getParameter("renewalDate");
     
      Computer computer = new Computer(id, name, available, imageURL, canBeLoaned, brand, model, serialNumber, memorySize, isLaptop, screenSize, date, renewalDate, processor, graphicCard);
      computerDAO.add(computer);
    }
    else if (category.equals("computerAccessory")) {
      ComputerAccessory computerAccessory = new ComputerAccessory(id, name, available, imageURL, canBeLoaned);
      ComputerAccessoryDAO caDAO = new ComputerAccessoryDAO();
      caDAO.add(computerAccessory);
    }
    else if (category.equals("vehicleAccessory")) {
      VehicleAccessory vehicleAccessory = new VehicleAccessory(id, name, available, imageURL, canBeLoaned);
      VehicleAccessoryDAO  vaDAO = new VehicleAccessoryDAO();
      vaDAO.add(vehicleAccessory);
    }
	}
}
