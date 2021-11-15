package test;

import org.junit.Test;

import model.dao.EquipmentDAO;
import model.dao.VehicleDAO;
import model.object.equipment.Equipment;
import model.object.equipment.Vehicle;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

public class VehicleDAOTest {
	
	@Test
	public void getTest() {
		VehicleDAO vehicleDAO = new VehicleDAO();
		int id = 1;
		int kilometers = 1000;
		int renewalKilometers = 6000;
		String brand = "Fiat";
		String model = "500";
		String registrationNumber = "AA-000-AA";
		String state = "GOOD";
		int maxSpeed = 180;
		int numberOfSpeeds = 5;
		int power = 80;
		
		Vehicle vehicle = vehicleDAO.get(id);
		
		assertEquals(vehicle != null, true);
		assertEquals(vehicle.getId(), 1);
		
		assertEquals(vehicle.getName(), "random equipment");
		assertEquals(vehicle.isAvailable(), true);
		assertEquals(vehicle.getImageUrl(), "http://randomURL.com");
		
		assertEquals(vehicle.getKilometers(), kilometers);
		assertEquals(vehicle.getRenewalKilometers(), renewalKilometers);
		assertEquals(vehicle.getBrand(), brand);
		assertEquals(vehicle.getModel(), model);
		assertEquals(vehicle.getRegistrationNumber(), registrationNumber);
		assertEquals(vehicle.getState(), state);
		assertEquals(vehicle.getMaxSpeed(), maxSpeed);
		assertEquals(vehicle.getNumberOfSpeeds(), numberOfSpeeds);
		assertEquals(vehicle.getPower(), power);
		
	}
 	
	@Test
	public void addTest() {
		VehicleDAO vehicleDAO = new VehicleDAO();
		int id = 2;
		
		String name = "voiture peugeot";
		boolean available = true;
		String imageUrl = "http://peugeot.com/208.png";
		
		int kilometers = 1000;
		int renewalKilometers = 6000;
		String brand = "Peugeot";
		String model = "208";
		String registrationNumber = "BB-111-AA";
		String state = "AVERAGE";
		int maxSpeed = 200;
		int numberOfSpeeds = 6;
		int power = 180;
		
		if(vehicleDAO.get(id) != null ) {
			vehicleDAO.deleteById(id);
		}
		
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		
		if(equipmentDAO.get(id) == null) {
			equipmentDAO.add(new Equipment(id, name, available, imageUrl, true));
		}
		
		Vehicle vehicle = new Vehicle(id, name, available, imageUrl, true, kilometers, brand, state, maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers);
		
		vehicleDAO.add(vehicle);
		
		Vehicle check = vehicleDAO.get(id);
		assertEquals(check != null, true);
		assertEquals(check.getId(), 2);
		
		assertEquals(check.getName(), name);
		assertEquals(check.isAvailable(), true);
		assertEquals(check.getImageUrl(), imageUrl);
		
		assertEquals(check.getKilometers(), kilometers);
		assertEquals(check.getRenewalKilometers(), renewalKilometers);
		assertEquals(check.getBrand(), brand);
		assertEquals(check.getModel(), model);
		assertEquals(check.getRegistrationNumber(), registrationNumber);
		assertEquals(check.getState(), state);
		assertEquals(check.getMaxSpeed(), maxSpeed);
		assertEquals(check.getNumberOfSpeeds(), numberOfSpeeds);
		assertEquals(check.getPower(), power);
		
		vehicleDAO.deleteById(id);
		equipmentDAO.deleteById(id);
		
	}
	
	@Test
	public void deleteTest() {
		VehicleDAO vehicleDAO = new VehicleDAO();
		int id = 2;
		
		String name = "voiture peugeot";
		boolean available = true;
		String imageUrl = "http://peugeot.com/208.png";
		
		int kilometers = 1000;
		int renewalKilometers = 6000;
		String brand = "Peugeot";
		String model = "208";
		String registrationNumber = "BB-111-AA";
		String state = "AVERAGE";
		int maxSpeed = 200;
		int numberOfSpeeds = 6;
		int power = 180;
		
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		
		Vehicle vehicle = new Vehicle(id, name, available, imageUrl, true, kilometers, brand, state, maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers);
		
		if(equipmentDAO.get(id) == null) {
			equipmentDAO.add(new Equipment(id, name, available, imageUrl, true));
		}
		
		if(vehicleDAO.get(id) == null ) {
			vehicleDAO.add(vehicle);
		}
		
		vehicleDAO.delete(vehicle);
		
		assertEquals(vehicleDAO.get(id) == null, true);
		
		
		
	}
	
	@Test
	public void deleteByIdTest() {
		VehicleDAO vehicleDAO = new VehicleDAO();
		int id = 2;
		
		String name = "voiture peugeot";
		boolean available = true;
		String imageUrl = "http://peugeot.com/208.png";
		
		int kilometers = 1000;
		int renewalKilometers = 6000;
		String brand = "Peugeot";
		String model = "208";
		String registrationNumber = "BB-111-AA";
		String state = "AVERAGE";
		int maxSpeed = 200;
		int numberOfSpeeds = 6;
		int power = 180;
		
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		
		Vehicle vehicle = new Vehicle(id, name, available, imageUrl, true, kilometers, brand, state, maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers);
		
		if(equipmentDAO.get(id) == null) {
			equipmentDAO.add(new Equipment(id, name, available, imageUrl, true));
		}
		
		if(vehicleDAO.get(id) == null ) {
			vehicleDAO.add(vehicle);
		}
		
		vehicleDAO.deleteById(id);
		
		assertEquals(vehicleDAO.get(id) == null, true);
	}
	
	@Test
	public void listAllTest() {
		VehicleDAO vehicleDAO = new VehicleDAO();
		ArrayList<Vehicle> list = vehicleDAO.listAll();
		
		assertEquals(list.size(), 1);
		
	}
	
	@Test
	public void update() {
		VehicleDAO vehicleDAO = new VehicleDAO();
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		int id = 2;
		
		String name = "voiture peugeot";
		boolean available = true;
		String imageUrl = "http://peugeot.com/208.png";
		
		int kilometers = 1000;
		int renewalKilometers = 6000;
		String brand = "Peugeot";
		String model = "208";
		String registrationNumber = "BB-111-AA";
		String state = "AVERAGE";
		int maxSpeed = 200;
		int numberOfSpeeds = 6;
		int power = 180;
		
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		
		Vehicle vehicle = new Vehicle(id, name, available, imageUrl, true, kilometers, brand, state, maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers);
		
		if(equipmentDAO.get(id) == null) {
			equipmentDAO.add(new Equipment(id, name, available, imageUrl, true));
		}
		
		if(vehicleDAO.get(id) == null ) {
			vehicleDAO.add(vehicle);
		}
		
		int newKilometers = 2000;
		int newRenewalKilometers = 7000;
		String newBrand = "Renault";
		String newModel = "Clio";
		String newRegistrationNumber = "CC-111-AA";
		String newState = "GOOD";
		int newMaxSpeed = 220;
		int newNumberOfSpeeds = 4;
		int newPower = 50;
		
		params.put("kilometers", newKilometers);
		params.put("renewalKilometers", newRenewalKilometers);
		params.put("brand", newBrand);
		params.put("model", newModel);
		params.put("registrationNumber", newRegistrationNumber);
		params.put("state", newState);
		params.put("maxSpeed", newMaxSpeed);
		params.put("numberOfSpeeds", newNumberOfSpeeds);
		params.put("power", newPower);
		
		vehicleDAO.update(vehicle, params);
		
		Vehicle check = vehicleDAO.get(id);
		
		assertEquals(check != null, true);
		assertEquals(check.getId(), 2);
		
		assertEquals(check.getKilometers(), newKilometers);
		assertEquals(check.getRenewalKilometers(), newRenewalKilometers);
		assertEquals(check.getBrand(), newBrand);
		assertEquals(check.getModel(), newModel);
		assertEquals(check.getRegistrationNumber(), newRegistrationNumber);
		assertEquals(check.getState(), newState);
		assertEquals(check.getMaxSpeed(), newMaxSpeed);
		assertEquals(check.getNumberOfSpeeds(), newNumberOfSpeeds);
		assertEquals(check.getPower(), newPower);
		
		vehicleDAO.deleteById(id);
		equipmentDAO.deleteById(id);

		
	}

}