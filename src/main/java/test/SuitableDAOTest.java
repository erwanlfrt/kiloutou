package test;

import org.junit.Test;

import model.dao.SuitableVehicleDAO;
import model.dao.VehicleAccessoryDAO;
import model.dao.VehicleDAO;
import model.dao.EquipmentDAO;
import model.object.equipment.SuitableVehicle;
import model.object.equipment.Vehicle;
import model.object.equipment.VehicleAccessory;
import model.object.equipment.Equipment;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

public class SuitableDAOTest {
	
	@Test
	public void getTest() {
		SuitableVehicleDAO suitableVehicleDAO = new SuitableVehicleDAO();
		int accessoryId = 1;
		int vehicleId = 1;
		
		Integer[] id = {vehicleId, accessoryId};
		
		SuitableVehicle sv = suitableVehicleDAO.get(id);
		
		assertEquals(sv != null, true);
		assertEquals(sv.getVehicleAccessory().getId(), accessoryId);
		assertEquals(sv.getVehicle().getId(), vehicleId);
		
		
		
	}
	
	
 	
	@Test
	public void addTest() {
		int accessoryId  = 4;
		String name = "random equipment 4";
		boolean available = true;
		String imageUrl = "http://randomURL4.com";
		
		VehicleAccessory  vehicleAccessory = new VehicleAccessory(accessoryId, name, available, imageUrl, true);
		Integer[] id = {1, vehicleAccessory.getId()};
		SuitableVehicleDAO suitableVehicleDAO = new SuitableVehicleDAO();
		
		if(suitableVehicleDAO.get(id) != null) {
			suitableVehicleDAO.deleteById(id);
		}
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		
		if(equipmentDAO.get(accessoryId) == null ) {
			equipmentDAO.add(new Equipment(accessoryId, name, available, imageUrl, true));
		}
		
		VehicleAccessoryDAO vaDAO = new VehicleAccessoryDAO();
		if(vaDAO.get(accessoryId) == null ) {
			vaDAO.add(vehicleAccessory);
		}
		
		VehicleDAO vehicleDAO = new VehicleDAO();
		Vehicle vehicle= vehicleDAO.get(1);
		
		suitableVehicleDAO.add(new SuitableVehicle(vehicle, vehicleAccessory));
		
		SuitableVehicle check = suitableVehicleDAO.get(id);
		
		assertEquals(check != null, true);
		assertEquals(check.getVehicle().getId(), vehicle.getId());
		assertEquals(check.getVehicleAccessory().getId(), vehicleAccessory.getId());
		
		suitableVehicleDAO.delete(check);
		
	}
	
	
	
	@Test
	public void deleteTest() {
		int accessoryId  = 4;
		String name = "random equipment 4";
		boolean available = true;
		String imageUrl = "http://randomURL4.com";
		
		VehicleAccessory  vehicleAccessory = new VehicleAccessory(accessoryId, name, available, imageUrl, true);
		Integer[] id = {1, vehicleAccessory.getId()};
		SuitableVehicleDAO suitableVehicleDAO = new SuitableVehicleDAO();
		
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		
		if(equipmentDAO.get(accessoryId) == null ) {
			equipmentDAO.add(new Equipment(accessoryId, name, available, imageUrl, true));
		}
		
		VehicleAccessoryDAO vaDAO = new VehicleAccessoryDAO();
		if(vaDAO.get(accessoryId) == null ) {
			vaDAO.add(vehicleAccessory);
		}
		
		VehicleDAO vehicleDAO = new VehicleDAO();
		Vehicle vehicle= vehicleDAO.get(1);
		
		SuitableVehicle suitableVehicle = new SuitableVehicle(vehicle, vehicleAccessory);
		if(suitableVehicleDAO.get(id) == null) {
			suitableVehicleDAO.add(suitableVehicle);
		}
		
		suitableVehicleDAO.delete(suitableVehicle);
		
		SuitableVehicle check = suitableVehicleDAO.get(id);
		assertEquals(suitableVehicleDAO.get(id) == null, true);
		
	}
	
	
	
	@Test
	public void deleteByIdTest() {
		int accessoryId  = 4;
		String name = "random equipment 4";
		boolean available = true;
		String imageUrl = "http://randomURL4.com";
		
		VehicleAccessory  vehicleAccessory = new VehicleAccessory(accessoryId, name, available, imageUrl, true);
		VehicleDAO vehicleDAO = new VehicleDAO();
		Vehicle vehicle= vehicleDAO.get(1);
		Integer[] id = {vehicle.getId(), accessoryId};
		SuitableVehicleDAO suitableVehicleDAO = new SuitableVehicleDAO();
		
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		
		if(equipmentDAO.get(accessoryId) == null ) {
			equipmentDAO.add(new Equipment(accessoryId, name, available, imageUrl, true));
		}
		
		VehicleAccessoryDAO vaDAO = new VehicleAccessoryDAO();
		if(vaDAO.get(accessoryId) == null ) {
			vaDAO.add(vehicleAccessory);
		}
		
		
		
		SuitableVehicle suitableVehicle = new SuitableVehicle(vehicle, vehicleAccessory);
		if(suitableVehicleDAO.get(id) == null) {
			suitableVehicleDAO.add(suitableVehicle);
		}
		
		suitableVehicleDAO.deleteById(id);
		
		SuitableVehicle check = suitableVehicleDAO.get(id);
		assertEquals(suitableVehicleDAO.get(id) == null, true);
		
		ArrayList<SuitableVehicle> list = suitableVehicleDAO.listAll();
	}
	
	
	@Test
	public void listAllTest() {
		SuitableVehicleDAO suitableVehicleDAO = new SuitableVehicleDAO();
		ArrayList<SuitableVehicle> list = suitableVehicleDAO.listAll();
		assertEquals(list.size(), 1);
	}
	/*
	@Test
	public void update() {
		// nothing to update
		
	} */

}