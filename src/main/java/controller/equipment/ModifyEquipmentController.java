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


public class ModifyEquipmentController extends HttpServlet {
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
    int id = Integer.parseInt(req.getParameter("id"));

    EquipmentDAO equipmentDAO = new EquipmentDAO();
    Equipment equipment = equipmentDAO.get(id);

    if(equipment != null) {
      req.setAttribute("equipment", equipment);

      //check if equipment is a vehicle
      VehicleDAO vehicleDAO = new VehicleDAO();
      Vehicle vehicle = vehicleDAO.get(id);

      if(vehicle != null) {
        req.setAttribute("vehicle", vehicle);

        //check if equipment is a car
        CarDAO carDAO = new CarDAO();
        Car car = carDAO.get(id);

        if(car != null) {
          req.setAttribute("car", car);
        }
        else {
          //check if equipment is a bike
          BikeDAO bikeDAO = new BikeDAO();
          Bike bike = bikeDAO.get(id);

          if(bike != null) {
            req.setAttribute("bike", bike);
          }
        }
      }
      else {
        //check if equipment is a computer
        ComputerDAO computerDAO = new ComputerDAO();
        Computer computer = computerDAO.get(id);

        if(computer != null) {
          req.setAttribute("computer", computer);
        }
        else {
          //check if equipment is a computer accessory
          ComputerAccessoryDAO computerAccessoryDAO = new ComputerAccessoryDAO();
          ComputerAccessory computerAccessory = computerAccessoryDAO.get(id);

          if(computerAccessory != null) {
            req.setAttribute("computerAccessory", computerAccessory);
          }
          else {
            //check if equipment is a vheicle accessory
            VehicleAccessoryDAO vehicleAccessoryDAO = new VehicleAccessoryDAO();
            VehicleAccessory vehicleAccessory = vehicleAccessoryDAO.get(id);

            if(vehicleAccessory != null) {
              req.setAttribute("vehicleAccessory", vehicleAccessory);
            }
          }
        }
      }

    }
    else {
      String pageName = "/error";
	
      RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);
      try {
        rd.forward(req, resp);
      } catch (ServletException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

		this.doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    EquipmentDAO equipmentDAO = new EquipmentDAO();
    HashMap<String, Object> params = new HashMap<String, Object>();
    

    String category = req.getParameter("categories");

    int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		boolean available = req.getParameter("available").equals("available");
    String imageURL = req.getParameter("imageURL");

    params.put("name", name);
    params.put("available", available);
    params.put("imageURL", imageURL);
    
    
    Equipment equipment = new Equipment(id, name, available, imageURL);

    equipmentDAO.update(equipment, params);

    String registrationNumber = req.getParameter("registrationNumber");
    String serialNumber = req.getParameter("serialNumber");

    //if vehicle is chosen
    if(registrationNumber != null) {
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


      Vehicle vehicle = new Vehicle(id, name, available, imageURL, kilometers, brand, state, maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers);
      VehicleDAO vehicleDAO = new VehicleDAO();
      vehicleDAO.update(vehicle, params);
      
      String numberOfSeatsString = req.getParameter("numberOfSeats");
      String numberOfCylindersString = req.getParameter("numberOfCylinders");
      
      if(numberOfSeatsString != null) {
    	  int numberOfSeats = Integer.parseInt(numberOfSeatsString);
        params = new HashMap<String, Object>();
        params.put("numberOfSeats", numberOfSeats);

    	  Car car = new Car(id, name, available, imageURL, kilometers, brand, state, maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers, numberOfSeats);
    	  CarDAO carDAO = new CarDAO();
    	  carDAO.update(car, params);
      }
      else if(numberOfCylindersString != null) {
    	  int numberOfCylinders = Integer.parseInt(numberOfCylindersString);
        params = new HashMap<String, Object>();
        params.put("numberOfCylinders", numberOfCylinders);
    	  Bike bike = new Bike(id, name, available, imageURL, kilometers, brand, state, maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers, numberOfCylinders);
    	  BikeDAO bikeDAO = new BikeDAO();
    	  bikeDAO.update(bike, params);
      }
      
    }
    //else if computer is chosen
    else if(serialNumber != null) {
      ComputerDAO computerDAO = new ComputerDAO();
      ProcessorDAO processorDAO = new ProcessorDAO();
      GraphicCardDAO graphicCardDAO = new GraphicCardDAO();
      
      Computer cmp = computerDAO.get(id);
      // add processor
      int processorId = cmp.getProcessor().getId();
      String processorBrand = req.getParameter("processorBrand");
      String processorName = req.getParameter("processorName");
      int numberOfCores = Integer.parseInt(req.getParameter("numberOfCores"));
      float processorFrequency = Float.parseFloat(req.getParameter("processorFrequency"));

      params = new HashMap<String, Object>();
      params.put("brand", processorBrand);
      params.put("name", processorName);
      params.put("numberOfCores", numberOfCores);
      params.put("frequency", processorFrequency);


      Processor processor = new Processor(processorId, processorName, processorBrand, numberOfCores, processorFrequency);
      processorDAO.update(processor, params);

      // add graphic card
      int graphicCardId = cmp.getGraphicCard().getId();
      String graphicCardBrand = req.getParameter("graphicCardBrand");
      String graphicCardName = req.getParameter("graphicCardName");
      float graphicCardFrequency = Float.parseFloat(req.getParameter("graphicCardFrequency"));
      
      params = new HashMap<String, Object>();
      params.put("brand", graphicCardBrand);
      params.put("name", graphicCardName);
      params.put("frequency", graphicCardFrequency);


      GraphicCard graphicCard = new GraphicCard(graphicCardId, graphicCardName, graphicCardBrand, graphicCardFrequency);
      graphicCardDAO.update(graphicCard, params);

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

      Computer computer = new Computer(id, name, available, imageURL, brand, model, serialNumber, memorySize, isLaptop, screenSize, LocalDate.parse(date), LocalDate.parse(renewalDate), processor, graphicCard);
      computerDAO.update(computer, params);
    }
    else if (category.equals("computerAccessory")) {
      ComputerAccessory computerAccessory = new ComputerAccessory(id, name, available, imageURL);
      ComputerAccessoryDAO caDAO = new ComputerAccessoryDAO();
      params = new HashMap<String, Object>();
      caDAO.update(computerAccessory, params);
    }
    else if (category.equals("vehicleAccessory")) {
      VehicleAccessory vehicleAccessory = new VehicleAccessory(id, name, available, imageURL);
      VehicleAccessoryDAO  vaDAO = new VehicleAccessoryDAO();
      params = new HashMap<String, Object>();
      vaDAO.update(vehicleAccessory, params);
    }

    
    String pageName="/welcome";
	
		RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
