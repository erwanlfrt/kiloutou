package test;

import org.junit.Test;

import model.dao.ComputerAccessoryDAO;
import model.dao.EquipmentDAO;
import model.object.equipment.ComputerAccessory;
import model.object.equipment.Equipment;

import static org.junit.Assert.assertEquals;

public class ComputerAccessoryDAOTest {

	@Test
	public void getTest() {
		ComputerAccessoryDAO computerAccessoryDAO = new ComputerAccessoryDAO();
		int id = 1;
		String name = "random equipment";
		String imageUrl = "http://randomURL.com";

		ComputerAccessory computerAccessory = computerAccessoryDAO.get(id);
		assertEquals(computerAccessory == null, false);
		assertEquals(computerAccessory.getId(), id);
		assertEquals(computerAccessory.getName(), name);
		assertEquals(computerAccessory.isAvailable(), true);
		assertEquals(computerAccessory.getImageUrl(), imageUrl);
	}

	@Test
	public void addTest() {
		int id = 4;
		String name = "random equipment 4";
		boolean available = true;
		String imageUrl = "http://randomURL4.com";

		ComputerAccessory computerAccessory = new ComputerAccessory(id, name, available, imageUrl, true);
		ComputerAccessoryDAO computerAccessoryDAO = new ComputerAccessoryDAO();

		if (computerAccessoryDAO.get(id) != null) {
			computerAccessoryDAO.delete(computerAccessory);
		}
		EquipmentDAO equipmentDAO = new EquipmentDAO();

		if (equipmentDAO.get(id) == null) {
			equipmentDAO.add(new Equipment(id, name, available, imageUrl, true));
		}

		computerAccessoryDAO.add(computerAccessory);

		ComputerAccessory check = computerAccessoryDAO.get(id);

		assertEquals(check != null, true);
		assertEquals(check.getId(), id);
		assertEquals(check.getName(), name);
		assertEquals(check.isAvailable(), true);
		assertEquals(check.getImageUrl(), imageUrl);

		computerAccessoryDAO.delete(check);

	}

	@Test
	public void deleteTest() {
		int id = 2;
		String name = "random computerAccessory 2";
		boolean available = true;
		String imageUrl = "http://randomURL2.com";

		ComputerAccessory computerAccessory = new ComputerAccessory(id, name, available, imageUrl, true);
		ComputerAccessoryDAO computerAccessoryDAO = new ComputerAccessoryDAO();

		if (computerAccessoryDAO.get(id) == null) {
			computerAccessoryDAO.add(computerAccessory);
		}

		computerAccessoryDAO.delete(computerAccessory);
		assertEquals(computerAccessoryDAO.get(id) == null, true);

	}

	@Test
	public void deleteByIdTest() {
		int id = 2;
		String name = "random computerAccessory 2";
		boolean available = true;
		String imageUrl = "http://randomURL2.com";

		ComputerAccessory computerAccessory = new ComputerAccessory(id, name, available, imageUrl, true);
		ComputerAccessoryDAO computerAccessoryDAO = new ComputerAccessoryDAO();

		if (computerAccessoryDAO.get(id) == null) {
			computerAccessoryDAO.add(computerAccessory);
		}

		computerAccessoryDAO.deleteById(id);
		assertEquals(computerAccessoryDAO.get(id) == null, true);
	}


	@Test
	public void update() {
		// nothing to update

	}

}