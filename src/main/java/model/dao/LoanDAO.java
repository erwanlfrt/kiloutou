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
import model.object.equipment.Equipment;
import model.object.user.User;
import model.object.loan.Loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LoanDAO implements Dao<Loan> {
	Connection connection;
	String table;
	
	public LoanDAO() {
		connection = DBManager.getInstance().getConnection();
		table = "Loan";
	}

  public void add(Loan object) {
    int equipmentId = object.getEquipment().getId();
    String userMail = object.getUser().getMail();
    LocalDate beginningDate = object.getBeginningDate();
    LocalDate endDate = object.getEndDate();
    boolean isBorrowed = object.isBorrowed();

    String query = "INSERT INTO " + this.table + " (equipmentId, userMail, beginningDate, endDate, isBorrowed ) VALUES ("+ equipmentId  + ", \'" + userMail +"\', \'" + this.javaDateToMysqlDate(beginningDate) +"\', \'" + this.javaDateToMysqlDate(endDate) +"\', " + isBorrowed+ ");";
    try {
      Statement statement = this.connection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
			e.printStackTrace();
		}
  }

  public void delete(Loan object) {
    int equipmentId = object.getEquipment().getId();
    String userMail = object.getUser().getMail();
    LocalDate beginningDate = object.getBeginningDate();
    String query = "DELETE FROM " + this.table + " WHERE equipmentId = " + equipmentId + " AND userMail = \'"+ userMail +"\' AND beginningDate = \'"+this.javaDateToMysqlDate(beginningDate)+"\';";

    try {
      Statement statement = this.connection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public void deleteById(Object id) {
	  if(id instanceof Object[]) {
		  Loan equipment = this.get(id);
		  this.delete(equipment);
	  }
  }

  public Loan get(Object id) {
    Loan result = null;
    if(id instanceof Object[]) {
    	Object equipmentIdObj = ((Object[])(id))[0];
    	int equipmentId = (Integer) equipmentIdObj;
    	
    	Object userMailObj = ((Object[])(id))[1];
    	String userMail = (String) userMailObj;
    	
    	Object beginningDateObj = ((Object[])(id))[2];
    	LocalDate beginningDate = (LocalDate) beginningDateObj;
    	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  	  	String formattedDate = beginningDate.format(myFormatObj);
    	
    	String query = "SELECT * from " + this.table + " WHERE equipmentId = " + equipmentId + " AND userMail = \'"+ userMail +"\' AND beginningDate = \'"+ formattedDate +"\';";
      try {
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()) {
          EquipmentDAO equipmentDAO = new EquipmentDAO();
          Equipment equipment = equipmentDAO.get(equipmentId);
          UserDAO userDAO = new UserDAO();
          User user = userDAO.get(userMail);

          result = new Loan(equipment, user, this.mysqlDateToJavaDate(rs.getString("beginningDate")), this.mysqlDateToJavaDate(rs.getString("endDate")), rs.getBoolean("isBorrowed"));
        }
      } catch(SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }
	
	public ArrayList<Loan> listAll() {
		ArrayList<Loan> result = new ArrayList<Loan>();
		try {
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM "+this.table + ";");
			while (rs.next()) {
		        EquipmentDAO equipmentDAO = new EquipmentDAO();
		        Equipment equipment = equipmentDAO.get(rs.getInt("equipmentId"));
		        UserDAO userDAO = new UserDAO();
		        User user = userDAO.get(rs.getString("userMail"));

				result.add(new Loan(equipment, user, this.mysqlDateToJavaDate(rs.getString("beginningDate")), this.mysqlDateToJavaDate(rs.getString("endDate")), rs.getBoolean("isBorrowed")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

  public void update(Loan object, HashMap<String, Object> parameters) {
    int equipmentId = object.getEquipment().getId();
    String userMail = object.getUser().getMail();
    String beginningDate = this.javaDateToMysqlDate(object.getBeginningDate());

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
      String query = "UPDATE " + this.table + " SET " + changes +" WHERE equipmentId = " +  equipmentId + " AND userMail = \'"+ userMail + "\' AND beginningDate = \'"+ beginningDate +"\';";
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
