package org.hust.model.Bike;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.ToString;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.hust.exception.InvalidIdException;
import org.hust.model.Database;

import java.lang.reflect.InvocationTargetException;

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

    public void unlock() {
        System.out.println("Bike unlocked");
        status = false;
    }

    public static Bike getBikeByID(String id) throws InvalidIdException, NoSuchMethodException {
        MongoDatabase db = Database.getConnection();
        MongoCollection<Document> bikeCollection = db.getCollection("bikes");
        Document bike = bikeCollection.find(new Document("_id", new ObjectId(id))).first();
        if (bike == null) {
            throw new InvalidIdException();
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
}
