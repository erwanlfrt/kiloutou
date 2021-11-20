package model.object.equipment;

public class GraphicCard {
	private int id;
	private String name;
	private String brand;
	private float frequency;

	public GraphicCard(int id, String name, String brand, float frequency) {
		this.id = id;
		this.name = name;
		this.brand = brand;
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
