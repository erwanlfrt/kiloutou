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
import model.Model;
import model.object.equipment.Equipment;
import model.object.equipment.VehicleAccessory;

public class VehicleAccessoryDAO extends Model implements Dao<VehicleAccessory> {

	public VehicleAccessoryDAO() {
		connection = DBManager.getInstance().getConnection();
		table = "VehicleAccessory";
	}

	public void add(VehicleAccessory object) {
		int id = object.getId();

		String query = "INSERT INTO " + this.table + " (id) VALUES (" + id + ");";
		try {
      this.refreshConnection();
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(VehicleAccessory object) {
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
			VehicleAccessory equipment = this.get(id);
			this.delete(equipment);
		}
	}

	public VehicleAccessory get(Object id) {
		VehicleAccessory result = null;
		if (id instanceof Integer) {
			String query = "SELECT * from " + this.table + " WHERE id = ? ;";
			try {
        this.refreshConnection();
				PreparedStatement statement = this.connection.prepareStatement(query);
				statement.setInt(1, (Integer) id);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					EquipmentDAO equipmentDAO = new EquipmentDAO();
					Equipment equipment = equipmentDAO.get(id);
					result = new VehicleAccessory(equipment.getId(), equipment.getName(), equipment.isAvailable(),
							equipment.getImageUrl(), equipment.canBeLoaned());
				}
				rs.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<VehicleAccessory> listAll() {
		ArrayList<VehicleAccessory> result = new ArrayList<VehicleAccessory>();
		try {
      this.refreshConnection();
			PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM " + this.table + ";");
			ResultSet rs = statement.executeQuery();
			EquipmentDAO equipmentDAO = new EquipmentDAO();
			while (rs.next()) {
				Equipment equipment = equipmentDAO.get(rs.getInt("id"));
				result.add(new VehicleAccessory(equipment.getId(), equipment.getName(), equipment.isAvailable(),
						equipment.getImageUrl(), equipment.canBeLoaned()));
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
					if (rs.getBoolean("canBeLoaned")) {
						Equipment e = new Equipment(rs.getInt("id"), rs.getString("name"), false, "",
								rs.getBoolean("canBeLoaned"));
						result.add(e);
					}
				}
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void update(VehicleAccessory object, HashMap<String, Object> parameters) {
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
