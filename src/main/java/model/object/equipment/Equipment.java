package model.object.equipment;

public class Equipment {
  protected int id;
  protected String name;
  protected boolean available;
  protected String imageUrl;

  public Equipment(int id, String name, boolean available, String imageUrl) {
    this.id = id;
    this.name = name;
    this.available = available;
    this.imageUrl = imageUrl;
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

}