package model.object.equipment;

public class GraphicCard {
  private int id;
  private String name;
  private String brand;
  private String gpu;
  private float frequency;
  
  public GraphicCard(int id, String name, String brand, String gpu, float frequency) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.gpu = gpu;
    this.frequency = frequency;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getGpu() {
    return gpu;
  }

  public void setGpu(String gpu) {
    this.gpu = gpu;
  }

  public float getFrequency() {
    return frequency;
  }

  public void setFrequency(float frequency) {
    this.frequency = frequency;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }
  
}
