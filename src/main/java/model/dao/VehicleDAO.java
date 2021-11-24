package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.DBManager;
import model.Model;
import model.object.equipment.Equipment;
import model.object.equipment.Vehicle;

public class VehicleDAO extends Model implements Dao<Vehicle> {

	public VehicleDAO() {
		connection = DBManager.getInstance().getConnection();
		table = "Vehicle";
	}

	public void add(Vehicle object) {
		int id = object.getId();

		int kilometers = object.getKilometers();
		String brand = object.getBrand();
		String state = object.getState();
		int maxSpeed = object.getMaxSpeed();
		int numberOfSpeeds = object.getNumberOfSpeeds();
		String model = object.getModel();
		int power = object.getPower();
		String registrationNumber = object.getRegistrationNumber();
		int renewalKilometers = object.getRenewalKilometers();

		String query = "INSERT INTO " + this.table
				+ " (id, kilometers, brand, state, maxSpeed, numberOfSpeeds, model, power, registrationNumber, renewalKilometers) VALUES ("
				+ id + ", " + kilometers + ", \'" + brand + "\', \'" + state + "\', " + maxSpeed + ", " + numberOfSpeeds
				+ ", \'" + model + "\', " + power + ", \'" + registrationNumber + "\', " + renewalKilometers + ");";
		try {
      this.refreshConnection();
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Vehicle object) {
		int id = object.getId();
		String query = "DELETE FROM " + this.table + " WHERE id = " + id + ";";

		try {
      this.refreshConnection();
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteById(Object id) {
		if (id instanceof Integer) {
			Vehicle equipment = this.get(id);
			this.delete(equipment);
		}
	}

	public Vehicle get(Object id) {
		Vehicle result = null;
		if (id instanceof Integer) {
			String query = "SELECT * from " + this.table + " WHERE id = ?;";
			try {
        this.refreshConnection();
				PreparedStatement statement = this.connection.prepareStatement(query);
				statement.setInt(1, (Integer) id);
				ResultSet rs = statement.executeQuery();
				EquipmentDAO equipmentDAO = new EquipmentDAO();
				while (rs.next()) {
					Equipment equipment = equipmentDAO.get(id);
					result = new Vehicle(equipment.getId(), equipment.getName(), equipment.isAvailable(),
							equipment.getImageUrl(), equipment.canBeLoaned(), rs.getInt("kilometers"),
							rs.getString("brand"), rs.getString("state"), rs.getInt("maxSpeed"),
							rs.getInt("numberOfSpeeds"), rs.getString("model"), rs.getInt("power"),
							rs.getString("registrationNumber"), rs.getInt("renewalKilometers"));
				}
				rs.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<Vehicle> listAll() {
		ArrayList<Vehicle> result = new ArrayList<Vehicle>();
		try {
      this.refreshConnection();
			PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM " + this.table + ";");
			ResultSet rs = statement.executeQuery();
			EquipmentDAO equipmentDAO = new EquipmentDAO();
			while (rs.next()) {
				Equipment equipment = equipmentDAO.get(rs.getInt("id"));
				result.add(new Vehicle(equipment.getId(), equipment.getName(), equipment.isAvailable(),
						equipment.getImageUrl(), equipment.canBeLoaned(), rs.getInt("kilometers"),
						rs.getString("brand"), rs.getString("state"), rs.getInt("maxSpeed"),
						rs.getInt("numberOfSpeeds"), rs.getString("model"), rs.getInt("power"),
						rs.getString("registrationNumber"), rs.getInt("renewalKilometers")));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Equipment> listAllIdAndName() {
		ArrayList<Equipment> result = new ArrayList<Equipment>();
		try {
      this.refreshConnection();
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery(
					"SELECT id, name, canBeLoaned FROM Equipment WHERE id IN (SELECT id FROM " + this.table + ") ;");
			while (rs.next()) {
				if (rs.getBoolean("canBeLoaned")) {
					Equipment e = new Equipment(rs.getInt("id"), rs.getString("name"), false, "",
							rs.getBoolean("canBeLoaned"));
					result.add(e);
				}
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void update(Vehicle object, HashMap<String, Object> parameters) {
		int id = object.getId();

		String changes = "";

		int numberOfChanges = parameters.values().size();
		int cpt = 1;

		for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
			String column = parameter.getKey();
			Object value = parameter.getValue();

			String change = "";

			if (value instanceof String) {
				change = column + " = \'" + (String) value + "\'" + (cpt != numberOfChanges ? ", " : " ");
			} else if (value instanceof Boolean || value instanceof Integer) {
				change = column + " = " + value.toString() + (cpt != numberOfChanges ? ", " : " ");
			}
			changes += change;
			cpt++;
		}
		if (!changes.equals("")) {
			String query = "UPDATE " + this.table + " SET " + changes + " WHERE id = " + id + ";";
			Statement statement;
			try {
        this.refreshConnection();
				statement = this.connection.createStatement();
				statement.executeUpdate(query);
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
