package model.object.equipment;

public class Bike extends Vehicle {
  private int numberOfCylinders;

  public Bike(int id, String name, boolean available, String imageUrl, boolean canBeLoaned, int kilometers, String brand, String state,
      int maxSpeed, int numberofSpeeds, String model, int power, String registrationNumber, int renewalKilometers,
      int numberOfCylinders) {
    super(id, name, available, imageUrl, canBeLoaned, kilometers, brand, state, maxSpeed, numberofSpeeds, model, power,
        registrationNumber, renewalKilometers);
    this.numberOfCylinders = numberOfCylinders;
  }

  public int getNumberOfCylinders() {
    return numberOfCylinders;
  }

  public void setNumberOfCylinders(int numberOfCylinders) {
    this.numberOfCylinders = numberOfCylinders;
  }

}
