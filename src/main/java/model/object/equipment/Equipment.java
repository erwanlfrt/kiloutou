package model.object.equipment;

import java.util.ArrayList;;

public class Equipment {
  protected int id;
  protected String name;
  protected boolean available;
  protected String imageUrl;
  protected ArrayList<String[]> loanedDates;
  protected boolean canBeLoaned;

  public Equipment(int id, String name, boolean available, String imageUrl, boolean canBeLoaned) {
    this.id = id;
    this.name = name;
    this.available = available;
    this.imageUrl = imageUrl;
    this.loanedDates =  new ArrayList<String[]>();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isAvailable() {
    return available;
  }

  public void available() {
    this.available = true;
  }

  public void unavailable() {
    this.available = false;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public void addPeriod(String beginningDate, String endDate) {
    String[] dates = new String[2];
    dates[0] = beginningDate;
    dates[1] = endDate;
    this.loanedDates.add(dates);
  }

  public ArrayList<String[]> getLoanedDates() {
    return this.loanedDates;
  }

  public boolean canBeLoaned() {
    return this.canBeLoaned;
  }

  public void setCanBeLoaned(boolean canBeLoaned) {
    this.canBeLoaned = canBeLoaned;
    
  } 

}