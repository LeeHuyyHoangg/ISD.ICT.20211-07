package org.hust.entity.station;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.hust.common.exception.InvalidIdException;
import org.hust.common.exception.InvalidLocationException;
import org.hust.entity.bike.Bike;
import org.hust.entity.db.Database;
import org.hust.utils.Utils;
import org.hust.views.popup.PopupScreen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Station class represents station entity
 *
 * @author hoang.lh194766
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Station {
    public static int CAPACITY = 30;
    public ObjectId _id = new ObjectId();
    private String location;
    private List<String> bikeIds;

    public Station(String location, List<String> bikeIds) {
        this.location = location;
        this.bikeIds = bikeIds;
    }

    @SneakyThrows
    public static Station getStationById(String id) {
        MongoDatabase db = Database.getConnection();
        MongoCollection<Document> stationCollection = db.getCollection("stations");
        Document station = stationCollection.find(new Document("_id", new ObjectId(id))).first();
        if (station == null) {
            PopupScreen.error(new InvalidIdException().getMessage());
            return null;
        }
        return Utils.documentToObject(station, Station.class);
    }

    @SneakyThrows
    public static Station getStationContainBike(String bikeId) {
        MongoDatabase db = Database.getConnection();
        MongoCollection<Document> stationCollection = db.getCollection("stations");
        Document station = stationCollection.find(new Document("bikeIds", bikeId)).first();
        if (station == null) {
            PopupScreen.error(new InvalidIdException().getMessage());
            return null;
        }
        return Utils.documentToObject(station, Station.class);
    }

    @SneakyThrows
    public static List<Station> getStationByLocation(String location) {
        MongoDatabase db = Database.getConnection();
        MongoCollection<Document> stationCollection = db.getCollection("stations");
        MongoCursor<Document> stations = stationCollection.find(new Document("location", location)).iterator();
        if (!stations.hasNext()) {
            PopupScreen.error(new InvalidLocationException().getMessage());
            return null;
        }
        List<Station> result = new LinkedList<>();
        while (stations.hasNext()) {
            result.add(Utils.documentToObject(stations.next(), Station.class));
        }
        return result;
    }

    public static List<Station> listAllStation() {
        MongoDatabase db = Database.getConnection();
        MongoCollection<Document> stationCollection = db.getCollection("stations");
        MongoCursor<Document> stations = stationCollection.find().iterator();

        List<Station> result = new ArrayList<>();
        while (stations.hasNext()) {
            result.add(Utils.documentToObject(stations.next(), Station.class));
        }

        return result;
    }

    public List<Bike> getStationBikes() {
        List<Bike> result = new ArrayList<>();
        for (String id : bikeIds) {
            result.add(Bike.getBikeById(id));
        }

        return result;
    }

    @Override
    public String toString() {
        return "Station at: " + this.location;
    }
}
