package model.object.equipment;

import java.util.ArrayList;

public class VehicleAccessory extends Equipment {
  private ArrayList<Vehicle> suitableVehicles;

  public VehicleAccessory(int id, String name, boolean available, String imageUrl) {
    super(id, name, available, imageUrl);
    this.suitableVehicles = new ArrayList<Vehicle>();
  }

  public ArrayList<Vehicle> getSuitableVehicles() {
    return (ArrayList<Vehicle>) suitableVehicles.clone();
  }

  public boolean addSuitableVehicle(Vehicle vehicle) {
    return this.suitableVehicles.add(vehicle);
  }

  public boolean deleteSuitableVehicle(Vehicle vehicle) {
    return this.suitableVehicles.remove(vehicle);
  } 


  

}
