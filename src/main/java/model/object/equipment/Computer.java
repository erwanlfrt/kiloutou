package model.object.equipment;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Computer extends Equipment {
  private String brand;
  private String model;
  private String serialNumber;
  private int memorySize;
  private boolean isLaptop;
  private int screenSize;
  private LocalDate purchaseDate;
  private LocalDate renewalDate;
  private Processor processor;
  private GraphicCard graphicCard;

  public Computer( int id, String name, boolean available, String imageUrl, String brand, String model, String serialNumber, int memorySize, boolean isLaptop, int screenSize, LocalDate purchaseDate, LocalDate renewalDate, Processor processor, GraphicCard graphicCard) {
    super(id, name, available, imageUrl);
    this.brand = brand;
    this.model = model;
    this.serialNumber = serialNumber;
    this.memorySize = memorySize;
    this.isLaptop = isLaptop;
    this.screenSize = screenSize;
    this.purchaseDate = purchaseDate;
    this.renewalDate = renewalDate;
    this.processor = processor;
    this.graphicCard = graphicCard;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public int getMemorySize() {
    return memorySize;
  }

  public void setMemorySize(int memorySize) {
    this.memorySize = memorySize;
  }

  public boolean isLaptop() {
    return isLaptop;
  }

  public void setLaptop(boolean isLaptop) {
    this.isLaptop = isLaptop;
  }

  public int getScreenSize() {
    return screenSize;
  }

  public void setScreenSize(int screenSize) {
    this.screenSize = screenSize;
  }

  public LocalDate getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(LocalDate purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  public LocalDate getRenewalDate() {
    return renewalDate;
  }

  public void setRenewalDate(LocalDate renewalDate) {
    this.renewalDate = renewalDate;
  }

  public Processor getProcessor() {
    return this.processor;
  }

  public void setProcessor(Processor processor) {
    this.processor = processor;
  }

  public GraphicCard getGraphicCard() {
    return this.graphicCard;
  }

  public void setGraphicCard(GraphicCard graphicCard) {
    this.graphicCard = graphicCard;
  }
  
  public String getStringPurchaseDate() {
	  DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	  String formattedDate = this.purchaseDate.format(myFormatObj);
	  return formattedDate;
  }
  
  public String getStringRenewalDate() {
	  DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	  String formattedDate = this.renewalDate.format(myFormatObj);
	  return formattedDate;
  }
  
}