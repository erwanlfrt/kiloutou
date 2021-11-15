package test;

import org.junit.Test;

import model.dao.CarDAO;
import model.dao.EquipmentDAO;
import model.dao.VehicleDAO;
import model.object.equipment.Car;
import model.object.equipment.Equipment;
import model.object.equipment.Vehicle;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

public class CarDAOTest {
	
	@Test
	public void getTest() {
		CarDAO carDAO = new CarDAO();
		
		int id = 1;
		String name = "random equipment";
		boolean available = true;
		String imageUrl = "http://randomURL.com";
		int numberOfSeats = 5;
		
		Car car = carDAO.get(id);
		
		assertEquals(car != null, true);
		assertEquals(car.getId(), id);
		assertEquals(car.getName(), name);
		assertEquals(car.isAvailable(), available);
		assertEquals(car.getImageUrl(), imageUrl);
		assertEquals(car.getNumberOfSeats(), numberOfSeats);
		
	}
 	
	@Test
	public void addTest() {
		CarDAO carDAO = new CarDAO();
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		
		int id = 1;
		String name = "random equipment";
		boolean available = true;
		String imageUrl = "http://randomURL.com";
		int numberOfSeats = 5;
		int kilometers = 1000;
		int renewalKilometers = 6000;
		String brand = "Fiat";
		String model = "500";
		String registrationNumber = "AA-000-AA";
		String state = "GOOD";
		int maxSpeed = 180;
		int numberOfSpeeds = 5;
		int power = 80;
		
		if(carDAO.get(id) != null ) {
			carDAO.deleteById(id);
		}
		
		if(equipmentDAO.get(id) == null ) {
			Equipment e = new Equipment(id, name, available, imageUrl, true);
			equipmentDAO.add(e);
		}
		
		Car car = new Car(id, name, available, imageUrl, true, kilometers, brand, state, maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers, numberOfSeats);
		
		carDAO.add(car);
		
		car = carDAO.get(id);
		
		assertEquals(car != null, true);
		assertEquals(car.getId(), id);
		assertEquals(car.getName(), name);
		assertEquals(car.isAvailable(), available);
		assertEquals(car.getImageUrl(), imageUrl);
		assertEquals(car.getKilometers(), kilometers);
		assertEquals(car.getRenewalKilometers(), renewalKilometers);
		assertEquals(car.getBrand(), brand);
		assertEquals(car.getModel(), model);
		assertEquals(car.getRegistrationNumber(), registrationNumber);
		assertEquals(car.getState(), state);
		assertEquals(car.getMaxSpeed(), maxSpeed);
		assertEquals(car.getNumberOfSpeeds(), numberOfSpeeds);
		assertEquals(car.getPower(), power);
		assertEquals(car.getNumberOfSeats(), numberOfSeats);
		
	}
	
	@Test
	public void deleteTest() {
		CarDAO carDAO = new CarDAO();
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		
		int id = 1;
		String name = "random equipment";
		boolean available = true;
		String imageUrl = "http://randomURL.com";
		int numberOfSeats = 5;
		int kilometers = 1000;
		int renewalKilometers = 6000;
		String brand = "Fiat";
		String model = "500";
		String registrationNumber = "AA-000-AA";
		String state = "GOOD";
		int maxSpeed = 180;
		int numberOfSpeeds = 5;
		int power = 80;
		
		Car car = new Car(id, name, available, imageUrl, true, kilometers, brand, state, maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers, numberOfSeats);
		
		
		if(carDAO.get(id) == null ) {
			carDAO.add(car);
		}
		
		if(equipmentDAO.get(id) == null ) {
			Equipment e = new Equipment(id, name, available, imageUrl, true);
			equipmentDAO.add(e);
		}
		carDAO.delete(car);
		assertEquals(carDAO.get(id) == null, true);
		
	}
	
	@Test
	public void deleteByIdTest() {
		CarDAO carDAO = new CarDAO();
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		VehicleDAO vehicleDAO = new VehicleDAO();
		
		int id = 2;
		String name = "random equipment";
		boolean available = true;
		String imageUrl = "http://randomURL.com";
		int numberOfSeats = 5;
		int kilometers = 1000;
		int renewalKilometers = 6000;
		String brand = "Fiat";
		String model = "500";
		String registrationNumber = "AA-000-AA";
		String state = "GOOD";
		int maxSpeed = 180;
		int numberOfSpeeds = 5;
		int power = 80;
		
		Car car = new Car(id, name, available, imageUrl, true, kilometers, brand, state, maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers, numberOfSeats);
		
		if(equipmentDAO.get(id) == null ) {
			Equipment e = new Equipment(id, name, available, imageUrl, true);
			equipmentDAO.add(e);
		}
		
		if(vehicleDAO.get(id) == null ) {
			Vehicle vehicle = new Vehicle(id, name, available, imageUrl, true, kilometers, brand, state, maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers);
			vehicleDAO.add(vehicle);
		}
		
		if(carDAO.get(id) == null ) {
			carDAO.add(car);
			
		}
		
		carDAO.deleteById(id);
		assertEquals(carDAO.get(id) == null, true);
	}
	
	@Test
	public void listAllTest() {
		CarDAO carDAO = new CarDAO();
		ArrayList<Car> list = carDAO.listAll();
		
		assertEquals(list.size(), 1);
	}
	
	@Test
	public void update() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		int newSeats = 4;
		params.put("numberOfSeats", newSeats);
		
		CarDAO carDAO = new CarDAO();
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		VehicleDAO vehicleDAO = new VehicleDAO();
		
		int id = 2;
		String name = "random equipment";
		boolean available = true;
		String imageUrl = "http://randomURL.com";
		int numberOfSeats = 5;
		int kilometers = 1000;
		int renewalKilometers = 6000;
		String brand = "Fiat";
		String model = "500";
		String registrationNumber = "AA-000-AA";
		String state = "GOOD";
		int maxSpeed = 180;
		int numberOfSpeeds = 5;
		int power = 80;
		
		Car car = new Car(id, name, available, imageUrl, true, kilometers, brand, state, maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers, numberOfSeats);
		
		if(equipmentDAO.get(id) == null ) {
			Equipment e = new Equipment(id, name, available, imageUrl, true);
			equipmentDAO.add(e);
		}
		
		if(vehicleDAO.get(id) == null ) {
			Vehicle vehicle = new Vehicle(id, name, available, imageUrl, true, kilometers, brand, state, maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers);
			vehicleDAO.add(vehicle);
		}
		
		if(carDAO.get(id) == null ) {
			carDAO.add(car);	
		}
		
		carDAO.update(car, params);
		car = carDAO.get(id);
		
		assertEquals(car != null, true);
		assertEquals(car.getId(), id);
		assertEquals(car.getName(), name);
		assertEquals(car.isAvailable(), available);
		assertEquals(car.getImageUrl(), imageUrl);
		assertEquals(car.getKilometers(), kilometers);
		assertEquals(car.getRenewalKilometers(), renewalKilometers);
		assertEquals(car.getBrand(), brand);
		assertEquals(car.getModel(), model);
		assertEquals(car.getRegistrationNumber(), registrationNumber);
		assertEquals(car.getState(), state);
		assertEquals(car.getMaxSpeed(), maxSpeed);
		assertEquals(car.getNumberOfSpeeds(), numberOfSpeeds);
		assertEquals(car.getPower(), power);
		assertEquals(car.getNumberOfSeats(), newSeats);
		
		carDAO.deleteById(id);
	}

}