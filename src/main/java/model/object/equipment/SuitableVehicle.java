package model.object.equipment;

public class SuitableVehicle {
  private Vehicle vehicle;
  private VehicleAccessory vehicleAccessory;

  public SuitableVehicle(Vehicle vehicle, VehicleAccessory vehicleAccessory) {
    this.vehicle = vehicle;
    this.vehicleAccessory = vehicleAccessory;
  }

  public Vehicle getVehicle() {
    return this.vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public VehicleAccessory getVehicleAccessory() {
    return this.vehicleAccessory;
  }

  public void setVehicleAccessory(VehicleAccessory vehicleAccessory) {
    this.vehicleAccessory = vehicleAccessory;
  }

}