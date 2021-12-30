package org.hust.entity.bike;

import org.bson.Document;
import org.hust.common.exception.InvalidBarcodeException;
import org.hust.entity.db.EcoBikeRentalDatabase;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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
    status = false;
  }
  
  public void getBike(String barcode) throws InvalidBarcodeException {
    MongoDatabase db = EcoBikeRentalDatabase.getConnection();
    MongoCollection<Document> bikeCollection = db.getCollection("bikes");
    Document bike = bikeCollection.find(new Document("barcode", barcode)).first();
    if (bike == null) {
      throw new InvalidBarcodeException();
    }
    MongoCollection<Document> bikeDetailsCollection = db.getCollection("bike_details");
    Document bikeDetails = bikeDetailsCollection.find(new Document("_id", bike.get("details"))).first();
    this.model = bike.getString("model");
    this.type = bike.getString("type");
    this.battery = bike.getInteger("battery");
    this.status = bike.getBoolean("status");
    this.usageTime = bike.getInteger("usageTime");
    this.speed = bikeDetails.getDouble("speed");
    this.color = bikeDetails.getString("color");
    this.weight = bikeDetails.getDouble("weight");
    this.description = bikeDetails.getString("description");
    this.value = bikeDetails.getInteger("value");
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
