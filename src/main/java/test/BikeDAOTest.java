package test;

import org.junit.Test;

import model.dao.BikeDAO;
import model.dao.EquipmentDAO;
import model.dao.VehicleDAO;
import model.object.equipment.Bike;
import model.object.equipment.Equipment;
import model.object.equipment.Vehicle;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

public class BikeDAOTest {

	@Test
	public void getTest() {
		BikeDAO bikeDAO = new BikeDAO();

		int id = 1;
		String name = "random equipment";
		boolean available = true;
		String imageUrl = "http://randomURL.com";
		int numberOfCylinders = 5;

		Bike bike = bikeDAO.get(id);

		assertEquals(bike != null, true);
		assertEquals(bike.getId(), id);
		assertEquals(bike.getName(), name);
		assertEquals(bike.isAvailable(), available);
		assertEquals(bike.getImageUrl(), imageUrl);
		assertEquals(bike.getNumberOfCylinders(), numberOfCylinders);

	}

	@Test
	public void addTest() {
		BikeDAO bikeDAO = new BikeDAO();
		EquipmentDAO equipmentDAO = new EquipmentDAO();

		int id = 1;
		String name = "random equipment";
		boolean available = true;
		String imageUrl = "http://randomURL.com";
		int numberOfCylinders = 5;
		int kilometers = 1000;
		int renewalKilometers = 6000;
		String brand = "Fiat";
		String model = "500";
		String registrationNumber = "AA-000-AA";
		String state = "GOOD";
		int maxSpeed = 180;
		int numberOfSpeeds = 5;
		int power = 80;

		if (bikeDAO.get(id) != null) {
			bikeDAO.deleteById(id);
		}

		if (equipmentDAO.get(id) == null) {
			Equipment e = new Equipment(id, name, available, imageUrl, true);
			equipmentDAO.add(e);
		}

		Bike bike = new Bike(id, name, available, imageUrl, true, kilometers, brand, state, maxSpeed, numberOfSpeeds,
				model, power, registrationNumber, renewalKilometers, numberOfCylinders);

		bikeDAO.add(bike);

		bike = bikeDAO.get(id);

		assertEquals(bike != null, true);
		assertEquals(bike.getId(), id);
		assertEquals(bike.getName(), name);
		assertEquals(bike.isAvailable(), available);
		assertEquals(bike.getImageUrl(), imageUrl);
		assertEquals(bike.getKilometers(), kilometers);
		assertEquals(bike.getRenewalKilometers(), renewalKilometers);
		assertEquals(bike.getBrand(), brand);
		assertEquals(bike.getModel(), model);
		assertEquals(bike.getRegistrationNumber(), registrationNumber);
		assertEquals(bike.getState(), state);
		assertEquals(bike.getMaxSpeed(), maxSpeed);
		assertEquals(bike.getNumberOfSpeeds(), numberOfSpeeds);
		assertEquals(bike.getPower(), power);
		assertEquals(bike.getNumberOfCylinders(), numberOfCylinders);

	}

	@Test
	public void deleteTest() {
		BikeDAO bikeDAO = new BikeDAO();
		EquipmentDAO equipmentDAO = new EquipmentDAO();

		int id = 1;
		String name = "random equipment";
		boolean available = true;
		String imageUrl = "http://randomURL.com";
		int numberOfCylinders = 5;
		int kilometers = 1000;
		int renewalKilometers = 6000;
		String brand = "Fiat";
		String model = "500";
		String registrationNumber = "AA-000-AA";
		String state = "GOOD";
		int maxSpeed = 180;
		int numberOfSpeeds = 5;
		int power = 80;

		Bike bike = new Bike(id, name, available, imageUrl, true, kilometers, brand, state, maxSpeed, numberOfSpeeds,
				model, power, registrationNumber, renewalKilometers, numberOfCylinders);

		if (bikeDAO.get(id) == null) {
			bikeDAO.add(bike);
		}

		if (equipmentDAO.get(id) == null) {
			Equipment e = new Equipment(id, name, available, imageUrl, true);
			equipmentDAO.add(e);
		}
		bikeDAO.delete(bike);
		assertEquals(bikeDAO.get(id) == null, true);

	}

	@Test
	public void deleteByIdTest() {
		BikeDAO bikeDAO = new BikeDAO();
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		VehicleDAO vehicleDAO = new VehicleDAO();

		int id = 2;
		String name = "random equipment";
		boolean available = true;
		String imageUrl = "http://randomURL.com";
		int numberOfCylinders = 5;
		int kilometers = 1000;
		int renewalKilometers = 6000;
		String brand = "Fiat";
		String model = "500";
		String registrationNumber = "AA-000-AA";
		String state = "GOOD";
		int maxSpeed = 180;
		int numberOfSpeeds = 5;
		int power = 80;

		Bike bike = new Bike(id, name, available, imageUrl, true, kilometers, brand, state, maxSpeed, numberOfSpeeds,
				model, power, registrationNumber, renewalKilometers, numberOfCylinders);

		if (equipmentDAO.get(id) == null) {
			Equipment e = new Equipment(id, name, available, imageUrl, true);
			equipmentDAO.add(e);
		}

		if (vehicleDAO.get(id) == null) {
			Vehicle vehicle = new Vehicle(id, name, available, imageUrl, true, kilometers, brand, state, maxSpeed,
					numberOfSpeeds, model, power, registrationNumber, renewalKilometers);
			vehicleDAO.add(vehicle);
		}

		if (bikeDAO.get(id) == null) {
			bikeDAO.add(bike);

		}

		bikeDAO.deleteById(id);
		assertEquals(bikeDAO.get(id) == null, true);
	}

	@Test
	public void listAllTest() {
		BikeDAO bikeDAO = new BikeDAO();
		ArrayList<Bike> list = bikeDAO.listAll();

		assertEquals(list.size(), 1);
	}

	@Test
	public void update() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		int newSeats = 4;
		params.put("numberOfCylinders", newSeats);

		BikeDAO bikeDAO = new BikeDAO();
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		VehicleDAO vehicleDAO = new VehicleDAO();

		int id = 2;
		String name = "random equipment";
		boolean available = true;
		String imageUrl = "http://randomURL.com";
		int numberOfCylinders = 5;
		int kilometers = 1000;
		int renewalKilometers = 6000;
		String brand = "Fiat";
		String model = "500";
		String registrationNumber = "AA-000-AA";
		String state = "GOOD";
		int maxSpeed = 180;
		int numberOfSpeeds = 5;
		int power = 80;

		Bike bike = new Bike(id, name, available, imageUrl, true, kilometers, brand, state, maxSpeed, numberOfSpeeds,
				model, power, registrationNumber, renewalKilometers, numberOfCylinders);

		if (equipmentDAO.get(id) == null) {
			Equipment e = new Equipment(id, name, available, imageUrl, true);
			equipmentDAO.add(e);
		}

		if (vehicleDAO.get(id) == null) {
			Vehicle vehicle = new Vehicle(id, name, available, imageUrl, true, kilometers, brand, state, maxSpeed,
					numberOfSpeeds, model, power, registrationNumber, renewalKilometers);
			vehicleDAO.add(vehicle);
		}

		if (bikeDAO.get(id) == null) {
			bikeDAO.add(bike);
		}

		bikeDAO.update(bike, params);
		bike = bikeDAO.get(id);

		assertEquals(bike != null, true);
		assertEquals(bike.getId(), id);
		assertEquals(bike.getName(), name);
		assertEquals(bike.isAvailable(), available);
		assertEquals(bike.getImageUrl(), imageUrl);
		assertEquals(bike.getKilometers(), kilometers);
		assertEquals(bike.getRenewalKilometers(), renewalKilometers);
		assertEquals(bike.getBrand(), brand);
		assertEquals(bike.getModel(), model);
		assertEquals(bike.getRegistrationNumber(), registrationNumber);
		assertEquals(bike.getState(), state);
		assertEquals(bike.getMaxSpeed(), maxSpeed);
		assertEquals(bike.getNumberOfSpeeds(), numberOfSpeeds);
		assertEquals(bike.getPower(), power);
		assertEquals(bike.getNumberOfCylinders(), newSeats);

		bikeDAO.deleteById(id);
	}

}