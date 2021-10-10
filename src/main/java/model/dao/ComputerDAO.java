package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import model.DBManager;
import model.object.equipment.Computer;
import model.object.equipment.Equipment;
import model.object.equipment.Processor;
import model.object.equipment.GraphicCard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ComputerDAO implements Dao<Computer> {
	Connection connection;
	String table;
	
	public ComputerDAO() {
		connection = DBManager.getInstance().getConnection();
		table = "Computer";
	}

  public void add(Computer object) {
    int id = object.getId();

    String brand = object.getBrand();
    String model = object.getModel();
    String serialNumber = object.getSerialNumber();
    int memorySize = object.getMemorySize();
    boolean isLaptop = object.isLaptop();
    int screenSize = object.getScreenSize();
    LocalDate renewalDate = object.getRenewalDate();
    LocalDate purchaseDate = object.getPurchaseDate();
    int processorId = object.getProcessor().getId();
    int graphicCardId = object.getGraphicCard().getId();
    

    String query = "INSERT INTO " + this.table + " (id, brand, model, serialNumber, memorySize, isLaptop, screenSize, purchaseDate, renewalDate, processorId, graphicCardId) VALUES ("+ id  + ", \'" + brand +"\', \'" + model +"\', \'" + serialNumber +"\', " + memorySize +", " + isLaptop +", " + screenSize +", \'" + this.javaDateToMysqlDate(purchaseDate) +"\', \'" + this.javaDateToMysqlDate(renewalDate) +"\', " + processorId+", " + graphicCardId +");";
    try {
      Statement statement = this.connection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
			e.printStackTrace();
		}
  }

  public void delete(Computer object) {
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
		  Computer equipment = this.get(id);
		  this.delete(equipment);
	  }
  }

  public Computer get(Object id) {
    Computer result = null;
    if(id instanceof Integer) {
    	String query = "SELECT * from " + this.table + " WHERE id = " + (Integer)id + ";";
      try {
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()) {
          EquipmentDAO equipmentDAO = new EquipmentDAO();
          Equipment equipment = equipmentDAO.get(id);
          ProcessorDAO processorDAO = new ProcessorDAO();
          Processor processor = processorDAO.get(rs.getInt("processorId"));
          GraphicCardDAO graphicCardDAO = new GraphicCardDAO();
          GraphicCard graphicCard = graphicCardDAO.get(rs.getInt("graphicCardId"));
          result = new Computer(equipment.getId(), equipment.getName(), equipment.isAvailable(), equipment.getImageUrl(), rs.getString("brand"),rs.getString("model"), rs.getString("serialNumber"), rs.getInt("memorySize"), rs.getBoolean("isLaptop"), rs.getInt("screenSize"), this.mysqlDateToJavaDate(rs.getString("purchaseDate")), this.mysqlDateToJavaDate(rs.getString("renewalDate")), processor, graphicCard );
        }
      } catch(SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }
	
	public ArrayList<Computer> listAll() {
		ArrayList<Computer> result = new ArrayList<Computer>();
		try {
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM "+this.table + ";");
			while (rs.next()) {
				EquipmentDAO equipmentDAO = new EquipmentDAO();
				Equipment equipment = equipmentDAO.get(rs.getInt("id"));
				ProcessorDAO processorDAO = new ProcessorDAO();
				Processor processor = processorDAO.get(rs.getInt("processorId"));
				GraphicCardDAO graphicCardDAO = new GraphicCardDAO();
				GraphicCard graphicCard = graphicCardDAO.get(rs.getInt("graphicCardId"));
				result.add(new Computer(equipment.getId(), equipment.getName(), equipment.isAvailable(), equipment.getImageUrl(), rs.getString("brand"),rs.getString("model"), rs.getString("serialNumber"), rs.getInt("memorySize"), rs.getBoolean("isLaptop"), rs.getInt("screenSize"), this.mysqlDateToJavaDate(rs.getString("purchaseDate")), this.mysqlDateToJavaDate(rs.getString("renewalDate")), processor, graphicCard ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

  public void update(Computer object, HashMap<String, Object> parameters) {
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

  private String javaDateToMysqlDate(LocalDate date) {
	  DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	  String formattedDate = date.format(myFormatObj);
	  return formattedDate;
  }

  private LocalDate mysqlDateToJavaDate(String mysqlDate) {
	  DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	  LocalDate result = LocalDate.parse(mysqlDate);
	  return result;
  }
}
