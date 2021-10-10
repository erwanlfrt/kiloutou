package model.object.equipment;

public class Vehicle extends Equipment{
  protected int kilometers;
  protected String brand;
  protected String state;
  protected int maxSpeed;
  protected int numberOfSpeeds;
  protected String model;
  protected int power;
  protected String registrationNumber;
  protected int renewalKilometers;
  
  public Vehicle(int id, String name, boolean available, String imageUrl, int kilometers, String brand, String state,
      int maxSpeed, int numberOfSpeeds, String model, int power, String registrationNumber, int renewalKilometers) {
    super(id, name, available, imageUrl);
    this.kilometers = kilometers;
    this.brand = brand;
    this.state = state;
    this.maxSpeed = maxSpeed;
    this.numberOfSpeeds = numberOfSpeeds;
    this.model = model;
    this.power = power;
    this.registrationNumber = registrationNumber;
    this.renewalKilometers = renewalKilometers;
  }

  public int getKilometers() {
    return kilometers;
  }

  public void setKilometers(int kilometers) {
    this.kilometers = kilometers;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public int getMaxSpeed() {
    return maxSpeed;
  }

  public void setMaxSpeed(int maxSpeed) {
    this.maxSpeed = maxSpeed;
  }

  public int getNumberOfSpeeds() {
    return numberOfSpeeds;
  }

  public void setNumberOfSpeeds(int numberOfSpeeds) {
    this.numberOfSpeeds = numberOfSpeeds;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getPower() {
    return power;
  }

  public void setPower(int power) {
    this.power = power;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public int getRenewalKilometers() {
    return renewalKilometers;
  }

  public void setRenewalKilometers(int renewalKilometers) {
    this.renewalKilometers = renewalKilometers;
  }

  
}
