package test;

import org.junit.Test;

import model.dao.GraphicCardDAO;
import model.object.equipment.GraphicCard;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphicCardDAOTest {

	@Test
	public void getTest() {
		GraphicCardDAO graphicCardDAO = new GraphicCardDAO();

		int id = 1;
		String name = "RTX1080";
		String brand = "Nvidia";
		float frequency = 3.5f;

		GraphicCard graphicCard = graphicCardDAO.get(id);

		assertEquals(graphicCard != null, true);
		assertEquals(graphicCard.getId(), id);
		assertEquals(graphicCard.getName(), name);
		assertEquals(graphicCard.getBrand(), brand);
		assertEquals(graphicCard.getFrequency(), frequency, 0.1);

	}

	@Test
	public void addTest() {
		GraphicCardDAO graphicCardDAO = new GraphicCardDAO();

		int id = 2;
		String name = "XX";
		String brand = "AMD";
		float frequency = 3.7f;

		if (graphicCardDAO.get(id) != null) {
			graphicCardDAO.deleteById(id);
		}

		GraphicCard graphicCard = new GraphicCard(id, name, brand, frequency);

		graphicCardDAO.add(graphicCard);

		GraphicCard check = graphicCardDAO.get(id);

		assertEquals(check != null, true);
		assertEquals(check.getId(), id);
		assertEquals(check.getName(), name);
		assertEquals(check.getBrand(), brand);
		assertEquals(check.getFrequency(), frequency, 0.1);

		graphicCardDAO.delete(check);

	}

	@Test
	public void deleteTest() {
		GraphicCardDAO graphicCardDAO = new GraphicCardDAO();

		int id = 2;
		String name = "XX";
		String brand = "AMD";
		float frequency = 3.7f;

		GraphicCard graphicCard = new GraphicCard(id, name, brand, frequency);

		if (graphicCardDAO.get(id) == null) {
			graphicCardDAO.add(graphicCard);
		}

		graphicCardDAO.delete(graphicCard);

		assertEquals(graphicCardDAO.get(id) == null, true);
	}

	@Test
	public void deleteByIdTest() {
		GraphicCardDAO graphicCardDAO = new GraphicCardDAO();

		int id = 2;
		String name = "XX";
		String brand = "AMD";
		float frequency = 3.7f;

		GraphicCard graphicCard = new GraphicCard(id, name, brand, frequency);

		if (graphicCardDAO.get(id) == null) {
			graphicCardDAO.add(graphicCard);
		}

		graphicCardDAO.deleteById(id);

		assertEquals(graphicCardDAO.get(id) == null, true);
	}

	@Test
	public void listAllTest() {
		GraphicCardDAO graphicCardDAO = new GraphicCardDAO();

		ArrayList<GraphicCard> list = graphicCardDAO.listAll();

		assertEquals(list.size(), 1);
	}

	@Test
	public void update() {
		GraphicCardDAO graphicCardDAO = new GraphicCardDAO();
		HashMap<String, Object> params = new HashMap<String, Object>();

		int id = 2;
		String name = "XX";
		String brand = "AMD";
		float frequency = 3.7f;

		GraphicCard graphicCard = new GraphicCard(id, name, brand, frequency);

		if (graphicCardDAO.get(id) == null) {
			graphicCardDAO.add(graphicCard);
		}

		String newName = "XY";
		String newBrand = "AZDKAZD";
		float newFrequency = 2f;

		params.put("name", newName);
		params.put("brand", newBrand);
		params.put("frequency", newFrequency);

		graphicCardDAO.update(graphicCard, params);

		GraphicCard check = graphicCardDAO.get(id);

		assertEquals(check != null, true);
		assertEquals(check.getId(), id);
		assertEquals(check.getName(), newName);
		assertEquals(check.getBrand(), newBrand);
		assertEquals(check.getFrequency(), newFrequency, 0.1);

		graphicCardDAO.delete(check);
	}
}