package model.object.loan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import model.object.equipment.Equipment;
import model.object.user.User;

public class Loan {
  private int id;
  private Equipment equipment;
  private User user;
  private LocalDate beginningDate;
  private LocalDate endDate;
  private boolean isBorrowed;
  
  public Loan(int id, Equipment equipment, User user, LocalDate beginningDate, LocalDate endDate, boolean isBorrowed) {
    this.id = id;
    this.equipment = equipment;
    this.user = user;
    this.beginningDate = beginningDate;
    this.endDate = endDate;
    this.isBorrowed = isBorrowed;
  }

  public int getId() {
    return this.id;
  }

  public Equipment getEquipment() {
    return equipment;
  }

  public User getUser() {
    return user;
  }

  public LocalDate getBeginningDate() {
    return beginningDate;
  }

  public void setBeginningDate(LocalDate beginningDate) {
    if(this.beginningDate == null) {
      this.beginningDate = beginningDate;
    }
    else {
      System.err.println("Loan.java -> setBeginningDate : beginningDate is not null. ");
    }
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    // TO DO check if this.material available between this.beginningDate and the new endDate.
    if(endDate.compareTo(this.beginningDate) > 0) {
      this.endDate = endDate;
    }
    else {
      System.err.println("Loan.java -> setEndDate : endDate is not valid.");
    }
    
  }

  public boolean isBorrowed() {
    return isBorrowed;
  }

  public void setBorrowed(boolean isBorrowed) {
    this.isBorrowed = isBorrowed;
  }
  
  public String getStringBeginningDate() {
	  DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	  String formattedDate = this.beginningDate.format(myFormatObj);
	  return formattedDate;
  }
  
  public String getStringEndDate() {
	  DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	  String formattedDate = this.endDate.format(myFormatObj);
	  return formattedDate;
  }

  
}
