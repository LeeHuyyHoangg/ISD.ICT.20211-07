package org.hust.entity.bike;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.hust.common.exception.InvalidBarcodeException;
import org.hust.common.exception.InvalidIdException;
import org.hust.controller.RentBikeController;
import org.hust.entity.db.Database;
import org.hust.entity.station.Station;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * Bike class is an abstract class that all types of bike need to extend from
 *
 * @author hoang.lh194766
 * @implNote To operate properly, the child class need to have:
 * <ul>
 *     <li><code>@Getter</code> annotation</li>
 *     <li><code>@NoArgsConstructor</code> annotation</li>
 *     <li><code>documentToBike()</code> implemented</li>
 *     <li><code>priceCoefficient()</code> implemented</li>
 * </ul>
 */
@Getter
@NoArgsConstructor
public abstract class Bike {
    private final ObjectId _id = new ObjectId();
    protected Class bikeType;
    private String model;
    private boolean status;
    private double speed;
    private String color;
    private double weight;
    private String description;
    private int value;
    private String barcode;
    private String imgUrl;

    protected Bike(String model, boolean status, double speed, String color,
                   double weight, String description, int value, String barcode, String imgUrl) {

        this.model = model;
        this.status = status;
        this.speed = speed;
        this.color = color;
        this.weight = weight;
        this.description = description;
        this.value = value;
        this.barcode = barcode;
        this.imgUrl = imgUrl;
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

    @SneakyThrows
    public static Bike getBikeById(String id) {
        MongoDatabase db = Database.getConnection();
        MongoCollection<Document> bikeCollection = db.getCollection("bikes");
        Document bike = bikeCollection.find(new Document("_id", new ObjectId(id))).first();


        if (bike == null) {
            System.out.println(id);
            throw new InvalidIdException();
        }

        try {
            Class bikeType = Class.forName(bike.getString("bikeType"));
            Reflections reflections = new Reflections(Bike.class);
            for (Class<? extends Bike> c : reflections.getSubTypesOf(Bike.class)) {
                if (bikeType.equals(c)) {
                    return (c.getDeclaredConstructor().newInstance()).documentToBike(bike);
                }
            }
            throw new ClassNotFoundException();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return (new NormalBike()).documentToBike(bike);
        }
    }

    public void unlock() {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", this._id);

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("status", false);

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        Database.getConnection().getCollection("bikes").updateOne(query, updateObject);
        status = true;

        RentBikeController.setCurrentlyRentedBike(this);
    }

    /**
     * the abstract function require the children class to generate a function
     * to set a document to that children class
     *
     * @param document the document needed to be cast
     * @return the child class object
     */
    public abstract Bike documentToBike(Document document);

    @Override
    public String toString() {
        return model + " - " + this.getBikeType();
    }

    public String getLocation() {
        return Objects.requireNonNull(Station.getStationContainBike(this._id.toString())).getLocation();
    }

    public long getRentTime() {
        return 10;
    }

    public boolean isAvailable() {
        return status;
    }

    public String getType() {
        String type = this.bikeType.toString();
        return type.substring(type.lastIndexOf('.') + 1);
    }

    public abstract double getPriceCoefficient();

    public abstract String getBikeType();

    public static class BikeDeserializer extends StdDeserializer<Bike> {
        public BikeDeserializer() {
            this(null);
        }

        protected BikeDeserializer(final Class<?> vc) {
            super(vc);
        }

        @SneakyThrows
        @Override
        public Bike deserialize(final JsonParser parser, final DeserializationContext context) {
            final JsonNode node = parser.getCodec().readTree(parser);
            final ObjectMapper mapper = (ObjectMapper) parser.getCodec();
            Class bikeType = Class.forName(node.get("bikeType").textValue());
            Reflections reflections = new Reflections(Bike.class);
            for (Class<? extends Bike> c : reflections.getSubTypesOf(Bike.class)) {
                if (bikeType.equals(c)) {
                    return mapper.treeToValue(node, c);
                }
            }
            return mapper.treeToValue(node, NormalBike.class);
        }
    }
}
