package test;

import org.junit.Test;

import model.dao.EquipmentDAO;
import model.object.equipment.Equipment;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

public class EquipmentDAOTest {
	
	@Test
	public void getTest() {
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		int id  = 1;
		String name = "random equipment";
		boolean available = true;
		String imageUrl = "http://randomURL.com";
		
		Equipment equipment = equipmentDAO.get(id);
		assertEquals(equipment == null, false);
		assertEquals(equipment.getId(), id);
		assertEquals(equipment.getName(), name);
		assertEquals(equipment.isAvailable(), true);
		assertEquals(equipment.getImageUrl(), imageUrl);
	}
 	
	@Test
	public void addTest() {
		int id  = 2;
		String name = "random equipment 2";
		boolean available = true;
		String imageUrl = "http://randomURL2.com";
		
		Equipment equipment = new Equipment(id, name, available, imageUrl);
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		if(equipmentDAO.get(equipment) != null) {
			equipmentDAO.delete(equipment);
		}
		equipmentDAO.add(equipment);
		
		Equipment check = equipmentDAO.get(id);
		assertEquals(check != null, true);
		assertEquals(check.getId(), 2);
		assertEquals(check.getName(), name);
		assertEquals(check.isAvailable(), true);
		assertEquals(check.getImageUrl(), imageUrl);
		
	}
	
	@Test
	public void deleteTest() {
		int id  = 2;
		String name = "random equipment 2";
		boolean available = true;
		String imageUrl = "http://randomURL2.com";
		
		Equipment equipment = new Equipment(id, name, available, imageUrl);
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		
		if(equipmentDAO.get(id) == null) {
			equipmentDAO.add(equipment);
		}
		
		equipmentDAO.delete(equipment);
		assertEquals(equipmentDAO.get(id) == null, true);
		
	}
	
	@Test
	public void deleteByIdTest() {
		int id  = 2;
		String name = "random equipment 2";
		boolean available = true;
		String imageUrl = "http://randomURL2.com";
		
		Equipment equipment = new Equipment(id, name, available, imageUrl);
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		
		if(equipmentDAO.get(id) == null) {
			equipmentDAO.add(equipment);
		}
		
		equipmentDAO.deleteById(id);
		assertEquals(equipmentDAO.get(id) == null, true);
	}
	
	@Test
	public void listAllTest() {
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		ArrayList<Equipment> list = equipmentDAO.listAll();
		
		if(equipmentDAO.get(2) != null) {
			assertEquals(list.size(), 2);
		}
		else {
			assertEquals(list.size(),1);
		}
		
	}
	
	@Test
	public void update() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		int id  = 2;
		String name = "random equipment 2";
		boolean available = true;
		String imageUrl = "http://randomURL2.com";
		
		Equipment equipment = new Equipment(id, name, available, imageUrl);
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		
		if(equipmentDAO.get(id) == null) {
			equipmentDAO.add(equipment);
		}
		
		String newName = "random equipment 3";
		boolean newAvailable = false;
		String newImageUrl = "http://randomURL3.com";
		
		params.put("name", newName);
		params.put("available", newAvailable);
		params.put("imageUrl", newImageUrl);
		
		equipmentDAO.update(equipment, params);
		
		Equipment check = equipmentDAO.get(id);
		
		assertEquals(check != null, true);
		assertEquals(check.getId(), 2);
		assertEquals(check.getName(), newName);
		assertEquals(check.isAvailable(), newAvailable);
		assertEquals(check.getImageUrl(), newImageUrl);
		
	}

}
