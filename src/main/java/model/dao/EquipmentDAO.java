package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.Date;

import model.DBManager;
import model.Model;
import model.object.equipment.Equipment;

public class EquipmentDAO extends Model implements Dao<Equipment> {
	
	public EquipmentDAO() {
		connection = DBManager.getInstance().getConnection();
		table = "Equipment";
	}

	public void add(Equipment object) {
		int id = object.getId();
		String name = object.getName();
		boolean available = object.isAvailable();
		String imageUrl = object.getImageUrl();

		String query = "INSERT INTO " + this.table + " (id, name, available, imageUrl) VALUES (" + id + ", \'" + name
				+ "\', " + available + ", \'" + imageUrl + "\');";
		try {
      this.refreshConnection();
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Equipment object) {
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
			Equipment equipment = this.get(id);
			this.delete(equipment);
		}
	}

	public Equipment get(Object id) {
		Equipment result = null;
		if (id instanceof Integer) {
			String query = "SELECT * from " + this.table + " WHERE id = ? ;";
			try {
        this.refreshConnection();
				PreparedStatement statement = this.connection.prepareStatement(query);
				statement.setInt(1, (Integer) id);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					result = new Equipment(rs.getInt("id"), rs.getString("name"), rs.getBoolean("available"),
							rs.getString("imageUrl"), rs.getBoolean("canBeLoaned"));
				}
				rs.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int autoIncrementId() {
		int res = -1;
		String query = "SELECT MAX(id) AS maxId FROM " + this.table + ";";
		try {
      this.refreshConnection();
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				res = rs.getInt("maxId") + 1;
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Equipment> listAll() {
		ArrayList<Equipment> result = new ArrayList<Equipment>();
		try {
      this.refreshConnection();
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM " + this.table + ";");
			while (rs.next()) {
				int id = rs.getInt("id");
				Equipment e = new Equipment(id, rs.getString("name"), rs.getBoolean("available"),
						rs.getString("imageUrl"), rs.getBoolean("canBeLoaned"));

				Statement dateStatement = this.connection.createStatement();
				ResultSet rsDates = dateStatement
						.executeQuery("SELECT beginningDate, endDate FROM Loan WHERE equipmentId = " + id + ";");

				while (rsDates.next()) {
					e.addPeriod(rsDates.getString("beginningDate"), rsDates.getString("endDate"));
				}

				result.add(e);
				dateStatement.close();
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
			ResultSet rs = statement.executeQuery("SELECT id, name, canBeLoaned FROM " + this.table + ";");
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

	public void update(Equipment object, HashMap<String, Object> parameters) {
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
