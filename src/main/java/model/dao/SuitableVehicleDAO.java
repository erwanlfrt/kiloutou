package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.DBManager;
import model.Model;
import model.object.equipment.SuitableVehicle;
import model.object.equipment.Vehicle;
import model.object.equipment.VehicleAccessory;

public class SuitableVehicleDAO extends Model implements Dao<SuitableVehicle> {

	public SuitableVehicleDAO() {
		connection = DBManager.getInstance().getConnection();
		table = "SuitableVehicle";
	}

	public void add(SuitableVehicle object) {
		int vehicleId = object.getVehicle().getId();
		int vehicleAccessoryId = object.getVehicleAccessory().getId();

		String query = "INSERT INTO " + this.table + " (vehicleId, accessoryId) VALUES (" + vehicleId + ", "
				+ vehicleAccessoryId + ");";
		try {
      this.refreshConnection();
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(SuitableVehicle object) {
		int accessoryId = object.getVehicleAccessory().getId();
		int vehicleId = object.getVehicle().getId();
		String query = "DELETE FROM " + this.table + " WHERE VehicleId = " + vehicleId + " AND accessoryId = "
				+ accessoryId + ";";

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
		if (id instanceof Integer[]) {
			SuitableVehicle equipment = this.get(id);
			this.delete(equipment);
		}
	}

	public SuitableVehicle get(Object id) {
		SuitableVehicle result = null;
		if (id instanceof Integer[]) {
			String query = "SELECT * from " + this.table + " WHERE vehicleId = " + ((Integer[]) id)[0]
					+ " AND accessoryId = " + ((Integer[]) id)[1] + ";";
			try {
        this.refreshConnection();
				Statement statement = this.connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				while (rs.next()) {
					VehicleDAO vehicleDAO = new VehicleDAO();
					Vehicle vehicle = vehicleDAO.get(rs.getInt("vehicleId"));
					VehicleAccessoryDAO vehicleAccessoryDAO = new VehicleAccessoryDAO();
					VehicleAccessory vehicleAccessory = vehicleAccessoryDAO.get(rs.getInt("accessoryId"));
					result = new SuitableVehicle(vehicle, vehicleAccessory);
				}
				rs.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<SuitableVehicle> listAll() {
		ArrayList<SuitableVehicle> result = new ArrayList<SuitableVehicle>();
		try {
      this.refreshConnection();
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM " + this.table + ";");
			while (rs.next()) {
				VehicleDAO vehicleDAO = new VehicleDAO();
				Vehicle vehicle = vehicleDAO.get(rs.getInt("vehicleId"));
				VehicleAccessoryDAO vehicleAccessoryDAO = new VehicleAccessoryDAO();
				VehicleAccessory vehicleAccessory = vehicleAccessoryDAO.get(rs.getInt("accessoryId"));

				result.add(new SuitableVehicle(vehicle, vehicleAccessory));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void update(SuitableVehicle object, HashMap<String, Object> parameters) {
		int vehicleId = object.getVehicle().getId();
		int vehicleAccessoryId = object.getVehicleAccessory().getId();

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
			String query = "UPDATE " + this.table + " SET " + changes + " WHERE vehicleId = " + vehicleId
					+ " AND accessoryId = " + vehicleAccessoryId + ";";
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
