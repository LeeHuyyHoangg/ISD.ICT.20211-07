package org.hust.entity.db;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.hust.utils.Utils;

import java.util.logging.Logger;

public class Database {

    private static Logger LOGGER = Utils.getLogger("Database");
    private static MongoDatabase database;

    public static MongoDatabase getConnection() {
        if (database != null) {
            return database;
        }
        try {
            ConnectionString connectionString = new ConnectionString("mongodb+srv://Group_7_User:ahaha123@cluster0.hpjzw.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build();
            MongoClient mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase("CapstoneProject");
            LOGGER.info("Database connected successfully");
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
        return database;
    }

}
