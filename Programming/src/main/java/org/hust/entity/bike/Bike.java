package org.hust.entity.bike;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.ToString;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.hust.common.exception.InvalidBarcodeException;
import org.hust.controller.RentBikeController;
import org.hust.entity.db.Database;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@Getter
@ToString
public abstract class Bike {
	private ObjectId _id;
    private Class bikeType;
    private String model;
    private String type;
    private boolean status;
    private double speed;
    private String color;
    private double weight;
    private String description;
    private int value;
    private String barcode;
    private Date startRentTime;
  
  public void unlock() {
    BasicDBObject query = new BasicDBObject();
    query.put("_id", this._id);

    BasicDBObject newDocument = new BasicDBObject();
    newDocument.put("status", true);

    BasicDBObject updateObject = new BasicDBObject();
    updateObject.put("$set", newDocument);

    Database.getConnection().getCollection("bikes").updateOne(query, updateObject);
    status = true;
    
    RentBikeController.setCurrentlyRentedBike(this);
    this.startRentTime = new Date();
  }
  
  public static Bike getBike(String barcode) throws InvalidBarcodeException {
	  MongoDatabase db = Database.getConnection();
      MongoCollection<Document> bikeCollection = db.getCollection("bikes");
      Document bike = bikeCollection.find(new Document("barcode", barcode)).first();
      if (bike == null) {
          throw new InvalidBarcodeException();
      }

      try {
          Class bikeType = Class.forName(bike.getString("bikeType"));
          return ((Bike) bikeType.getDeclaredConstructor().newInstance()).documentToBike(bike);
      } catch (Exception e) {
          return (new NormalBike()).documentToBike(bike);
      }
  }

  public static Bike getBikeByID(String id) throws InvalidBarcodeException, NoSuchMethodException {
      MongoDatabase db = Database.getConnection();
      MongoCollection<Document> bikeCollection = db.getCollection("bikes");
      Document bike = bikeCollection.find(new Document("_id", new ObjectId(id))).first();
      if (bike == null) {
          throw new InvalidBarcodeException();
      }

      try {
          Class bikeType = Class.forName(bike.getString("bikeType"));
          return ((Bike) bikeType.getDeclaredConstructor().newInstance()).documentToBike(bike);
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
          return (new NormalBike()).documentToBike(bike);
      }
  }

  /**
   * the abstract function require the children class to generate a function
   * to set a document to that children class
   *
   * @param document the document needed to be cast
   * @return the child class object
   */
  protected abstract Bike documentToBike(Document document);
  
  public String getModel() {
    return this.model;
  }
  
  public String getType() {
    return this.type;
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
  
  public String getImageUrl() {
    return "ImageUrl";
  }
  
  public String getLocation() {
    return "Location";
  }
  
  /**
   * This method return the time this bike has been rented.
   * @return rentTime - the time this bike has been rented in miliseconds
   */
  public long getRentTime() {
    long rentTime = new Date().getTime() - this.startRentTime.getTime();
    return rentTime;
  }
}
