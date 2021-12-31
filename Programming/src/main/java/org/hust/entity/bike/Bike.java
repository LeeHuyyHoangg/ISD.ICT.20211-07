package org.hust.entity.bike;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.hust.common.exception.InvalidBarcodeException;
import org.hust.entity.db.EcoBikeRentalDatabase;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Bike {
  private ObjectId id;
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
    BasicDBObject query = new BasicDBObject();
    query.put("_id", this.id);

    BasicDBObject newDocument = new BasicDBObject();
    newDocument.put("status", true);

    BasicDBObject updateObject = new BasicDBObject();
    updateObject.put("$set", newDocument);

    EcoBikeRentalDatabase.getConnection().getCollection("bikes").updateOne(query, updateObject);
    status = true;
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
    this.id = bike.getObjectId("_id");
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
    if (this.status == true) {
      throw new InvalidBarcodeException();
    }
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
