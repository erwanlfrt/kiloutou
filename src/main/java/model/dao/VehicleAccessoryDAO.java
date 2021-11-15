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
import model.object.equipment.VehicleAccessory;
import model.object.equipment.Equipment;

public class VehicleAccessoryDAO implements Dao<VehicleAccessory> {
	Connection connection;
	String table;
	
	public VehicleAccessoryDAO() {
		connection = DBManager.getInstance().getConnection();
		table = "VehicleAccessory";
	}

  public void add(VehicleAccessory object) {
    int id = object.getId();

    String query = "INSERT INTO " + this.table + " (id) VALUES ("+ id  +");";
    try {
      Statement statement = this.connection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
			e.printStackTrace();
		}
  }

  public void delete(VehicleAccessory object) {
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
	  if(id instanceof Integer) {
		  VehicleAccessory equipment = this.get(id);
		  this.delete(equipment);
	  }
  }

  public VehicleAccessory get(Object id) {
    VehicleAccessory result = null;
    if(id instanceof Integer) {
    	String query = "SELECT * from " + this.table + " WHERE id = ? ;";
      try {
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setInt(1, (Integer)id);
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
          EquipmentDAO equipmentDAO = new EquipmentDAO();
          Equipment equipment = equipmentDAO.get(id);
          result = new VehicleAccessory(equipment.getId(), equipment.getName(), equipment.isAvailable(),equipment.getImageUrl(), equipment.canBeLoaned());
        }
      } catch(SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }
	
	public ArrayList<VehicleAccessory> listAll() {
		ArrayList<VehicleAccessory> result = new ArrayList<VehicleAccessory>();
		try {
			PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM "+ this.table + ";");
			ResultSet rs = statement.executeQuery();
      EquipmentDAO equipmentDAO = new EquipmentDAO();
			while (rs.next()) {
				Equipment equipment = equipmentDAO.get(rs.getInt("id"));
				result.add(new VehicleAccessory(equipment.getId(), equipment.getName(), equipment.isAvailable(),equipment.getImageUrl(), equipment.canBeLoaned()));
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
			ResultSet rs = statement.executeQuery("SELECT id, name FROM Equipment WHERE id IN (SELECT id FROM " + this.table +") ;");
			while (rs.next()) {
        Equipment e = new Equipment(rs.getInt("id"), rs.getString("name"),false,  "", true);
				result.add(e);
			}
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
      }
      else if (value instanceof Boolean || value instanceof Integer) {
        change = column + " = " + value.toString() + (cpt != numberOfChanges ? ", " : " ");
      }
      changes += change;
      cpt++;
    }
    if(!changes.equals("")) {
      String query = "UPDATE " + this.table + " SET " + changes +" WHERE id = " + id + ";";
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
