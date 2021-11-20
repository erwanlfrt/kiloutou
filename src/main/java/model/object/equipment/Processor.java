package model.object.equipment;

public class Processor {
	private int id;
	private String name;
	private String brand;
	private int numberOfCores;
	private float frequency;

	public Processor(int id, String name, String brand, int numberOfCores, float frequency) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.numberOfCores = numberOfCores;
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

	public int getNumberOfCores() {
		return numberOfCores;
	}

	public void setNumberOfCores(int numberOfCores) {
		this.numberOfCores = numberOfCores;
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
