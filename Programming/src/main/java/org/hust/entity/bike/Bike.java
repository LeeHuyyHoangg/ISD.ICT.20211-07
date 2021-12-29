package org.hust.entity.bike;

import org.hust.common.exception.InvalidBarcodeException;

public class Bike {
  private String model;
  private String type;
  private int battery;
  private boolean status;
  private int usageTime;
  private double speed;
  private String color;
  private double weight;
  private String description;
  private int value;
  
  public void unlock() {
    System.out.println("Bike unlocked");
    status = true;
  }
  
  public void getBike(String barcode) throws InvalidBarcodeException {
    value = 10000;
  }
  
  public String getModel() {
    return this.model;
  }
  
  public String getType() {
    return this.type;
  }
  
  public int getBattery() {
    return this.battery;
  }
  
  public int getUsageTime() {
    return this.usageTime;
  }
  
  public double getSpeed() {
    return this.speed;
  }
  
  public String getColor() {
    return this.color;
  }
  
  public double getWeight() {
    return this.weight;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public int getValue() {
    return this.value;
  }
  
}
