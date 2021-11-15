package model.object.equipment;

public class Car extends Vehicle {
  private int numberOfSeats;

  public Car(int id, String name, boolean available, String imageUrl, boolean canBeLoaned, int kilometers, String brand, String state,
      int maxSpeed, int numberofSpeeds, String model, int power, String registrationNumber, int renewalKilometers,
      int numberOfSeats) {
    super(id, name, available, imageUrl, canBeLoaned, kilometers, brand, state, maxSpeed, numberofSpeeds, model, power,
        registrationNumber, renewalKilometers);
    this.numberOfSeats = numberOfSeats;
  }

  public int getNumberOfSeats() {
    return numberOfSeats;
  }

  public void setNumberOfSeats(int numberOfSeats) {
    this.numberOfSeats = numberOfSeats;
  }
  
}
