package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.DBManager;
import model.object.equipment.Bike;
import model.object.equipment.Equipment;
import model.object.equipment.Vehicle;

public class BikeDAO implements Dao<Bike> {
	Connection connection;
	String table;

	public BikeDAO() {
		connection = DBManager.getInstance().getConnection();
		table = "Bike";
	}

	public void add(Bike object) {
		int id = object.getId();

		int numberOfCylinders = object.getNumberOfCylinders();

		String query = "INSERT INTO " + this.table + " (id, numberOfCylinders) VALUES (" + id + ", " + numberOfCylinders
				+ ");";
		try {
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Bike object) {
		int id = object.getId();
		String query = "DELETE FROM " + this.table + " WHERE id = " + id + ";";

		try {
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteById(Object id) {
		if (id instanceof Integer) {
			Bike vehicle = this.get(id);
			this.delete(vehicle);
		}
	}

	public Bike get(Object id) {
		Bike result = null;
		if (id instanceof Integer) {
			String query = "SELECT * from " + this.table + " WHERE id = ?;";
			try {
				PreparedStatement statement = this.connection.prepareStatement(query);
				statement.setInt(1, (Integer) id);
				ResultSet rs = statement.executeQuery(query);
				VehicleDAO vehicleDAO = new VehicleDAO();
				while (rs.next()) {
					Vehicle vehicle = vehicleDAO.get(id);
					result = new Bike(vehicle.getId(), vehicle.getName(), vehicle.isAvailable(), vehicle.getImageUrl(),
							vehicle.canBeLoaned(), vehicle.getKilometers(), vehicle.getBrand(), vehicle.getState(),
							vehicle.getMaxSpeed(), vehicle.getNumberOfSpeeds(), vehicle.getModel(), vehicle.getPower(),
							vehicle.getRegistrationNumber(), vehicle.getRenewalKilometers(),
							rs.getInt("numberOfCylinders"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<Bike> listAll() {
		ArrayList<Bike> result = new ArrayList<Bike>();
		try {
			PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM " + this.table + ";");
			ResultSet rs = statement.executeQuery();
			VehicleDAO vehicleDAO = new VehicleDAO();
			while (rs.next()) {
				Vehicle vehicle = vehicleDAO.get(rs.getInt("id"));
				result.add(new Bike(vehicle.getId(), vehicle.getName(), vehicle.isAvailable(), vehicle.getImageUrl(),
						vehicle.canBeLoaned(), vehicle.getKilometers(), vehicle.getBrand(), vehicle.getState(),
						vehicle.getMaxSpeed(), vehicle.getNumberOfSpeeds(), vehicle.getModel(), vehicle.getPower(),
						vehicle.getRegistrationNumber(), vehicle.getRenewalKilometers(),
						rs.getInt("numberOfCylinders")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Equipment> listAllIdAndName() {
		ArrayList<Equipment> result = new ArrayList<Equipment>();
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void update(Bike object, HashMap<String, Object> parameters) {
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
				statement = this.connection.createStatement();
				statement.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
