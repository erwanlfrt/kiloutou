package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.DBManager;
import model.Model;
import model.object.equipment.Car;
import model.object.equipment.Equipment;
import model.object.equipment.Vehicle;

public class CarDAO extends Model implements Dao<Car> {

	public CarDAO() {
		connection = DBManager.getInstance().getConnection();
		table = "Car";
	}

	public void add(Car object) {
		int id = object.getId();

		int numberOfSeats = object.getNumberOfSeats();

		String query = "INSERT INTO " + this.table + " (id, numberOfSeats) VALUES (" + id + ", " + numberOfSeats + ");";
		try {
      this.refreshConnection();
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Car object) {
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
			Car vehicle = this.get(id);
			this.delete(vehicle);
		}
	}

	public Car get(Object id) {
		Car result = null;
		if (id instanceof Integer) {
			String query = "SELECT * from " + this.table + " WHERE id = " + (Integer) id + ";";
			try {
        this.refreshConnection();
        Statement statement = this.connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				while (rs.next()) {
					VehicleDAO vehicleDAO = new VehicleDAO();
					Vehicle vehicle = vehicleDAO.get(id);
					result = new Car(vehicle.getId(), vehicle.getName(), vehicle.isAvailable(), vehicle.getImageUrl(),
							vehicle.canBeLoaned(), vehicle.getKilometers(), vehicle.getBrand(), vehicle.getState(),
							vehicle.getMaxSpeed(), vehicle.getNumberOfSpeeds(), vehicle.getModel(), vehicle.getPower(),
							vehicle.getRegistrationNumber(), vehicle.getRenewalKilometers(),
							rs.getInt("numberOfSeats"));
				}
				rs.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<Car> listAll() {
		ArrayList<Car> result = new ArrayList<Car>();
		try {
      this.refreshConnection();
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM " + this.table + ";");
			while (rs.next()) {
				VehicleDAO vehicleDAO = new VehicleDAO();
				Vehicle vehicle = vehicleDAO.get(rs.getInt("id"));
				result.add(new Car(vehicle.getId(), vehicle.getName(), vehicle.isAvailable(), vehicle.getImageUrl(),
						vehicle.canBeLoaned(), vehicle.getKilometers(), vehicle.getBrand(), vehicle.getState(),
						vehicle.getMaxSpeed(), vehicle.getNumberOfSpeeds(), vehicle.getModel(), vehicle.getPower(),
						vehicle.getRegistrationNumber(), vehicle.getRenewalKilometers(), rs.getInt("numberOfSeats")));
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

	public void update(Car object, HashMap<String, Object> parameters) {
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
