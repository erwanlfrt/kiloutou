package test;

import org.junit.Test;

import model.dao.VehicleAccessoryDAO;
import model.dao.EquipmentDAO;
import model.object.equipment.VehicleAccessory;
import model.object.equipment.Equipment;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

public class VehicleAccessoryDAOTest {
	
	@Test
	public void getTest() {
		VehicleAccessoryDAO vehicleAccessoryDAO = new VehicleAccessoryDAO();
		int id  = 1;
		String name = "random equipment";
		boolean available = true;
		String imageUrl = "http://randomURL.com";
		
		VehicleAccessory vehicleAccessory = vehicleAccessoryDAO.get(id);
		assertEquals(vehicleAccessory == null, false);
		assertEquals(vehicleAccessory.getId(), id);
		assertEquals(vehicleAccessory.getName(), name);
		assertEquals(vehicleAccessory.isAvailable(), true);
		assertEquals(vehicleAccessory.getImageUrl(), imageUrl);
	}
 	
	@Test
	public void addTest() {
		int id  = 4;
		String name = "random equipment 4";
		boolean available = true;
		String imageUrl = "http://randomURL4.com";
		
		VehicleAccessory vehicleAccessory = new VehicleAccessory(id, name, available, imageUrl, true);
		VehicleAccessoryDAO vehicleAccessoryDAO = new VehicleAccessoryDAO();
		
		if(vehicleAccessoryDAO.get(id) != null) {
			vehicleAccessoryDAO.delete(vehicleAccessory);
		}
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		
		if(equipmentDAO.get(id) == null ) {
			equipmentDAO.add(new Equipment(id, name, available, imageUrl, true));
		}
		
		vehicleAccessoryDAO.add(vehicleAccessory);
		
		VehicleAccessory check = vehicleAccessoryDAO.get(id);
		
		assertEquals(check != null, true);
		assertEquals(check.getId(), id);
		assertEquals(check.getName(), name);
		assertEquals(check.isAvailable(), true);
		assertEquals(check.getImageUrl(), imageUrl);
		
		vehicleAccessoryDAO.delete(check);
		
	}
	
	@Test
	public void deleteTest() {
		int id  = 2;
		String name = "random vehicleAccessory 2";
		boolean available = true;
		String imageUrl = "http://randomURL2.com";
		
		VehicleAccessory vehicleAccessory = new VehicleAccessory(id, name, available, imageUrl, true);
		VehicleAccessoryDAO vehicleAccessoryDAO = new VehicleAccessoryDAO();
		
		if(vehicleAccessoryDAO.get(id) == null) {
			vehicleAccessoryDAO.add(vehicleAccessory);
		}
		
		vehicleAccessoryDAO.delete(vehicleAccessory);
		assertEquals(vehicleAccessoryDAO.get(id) == null, true);
		
	}
	
	@Test
	public void deleteByIdTest() {
		int id  = 2;
		String name = "random vehicleAccessory 2";
		boolean available = true;
		String imageUrl = "http://randomURL2.com";
		
		VehicleAccessory vehicleAccessory = new VehicleAccessory(id, name, available, imageUrl, true);
		VehicleAccessoryDAO vehicleAccessoryDAO = new VehicleAccessoryDAO();
		
		if(vehicleAccessoryDAO.get(id) == null) {
			vehicleAccessoryDAO.add(vehicleAccessory);
		}
		
		vehicleAccessoryDAO.deleteById(id);
		assertEquals(vehicleAccessoryDAO.get(id) == null, true);
	}
	
	@Test
	public void listAllTest() {
		VehicleAccessoryDAO vehicleAccessoryDAO = new VehicleAccessoryDAO();
		ArrayList<VehicleAccessory> list = vehicleAccessoryDAO.listAll();
	}
	
	@Test
	public void update() {
		// nothing to update
		
	}

}