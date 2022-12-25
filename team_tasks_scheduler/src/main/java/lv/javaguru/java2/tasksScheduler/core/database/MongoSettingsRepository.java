package lv.javaguru.java2.tasksScheduler.core.database;


import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.result.InsertOneResult;
import lv.javaguru.java2.tasksScheduler.core.domain.Settings;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoClients;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.Map;

//@Component
public class MongoSettingsRepository implements SettingsRepository{

    @Override
    public boolean save(Settings settings) {
        InsertOneResult result;

        String uri = "mongodb://dbadmin:abc123@localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            var database = mongoClient.getDatabase("java2_taskscheduler");
            var todoCollection = database.getCollection("settings");


            var todoDocument = new Document(Map.of("_id", new ObjectId(),
                                                "adminPassword", settings.getAdminPassword(),
                                                "emailFrom", settings.getEmailFrom(),
                                                "emailPassword", settings.getEmailPassword(),
                                                "emailHost", settings.getEmailHost(),
                                                "emailPort", settings.getEmailPort(),
                                                "emailProtocol", settings.getEmailProtocol())
                                                );

            result = todoCollection.insertOne(todoDocument);

            return result.wasAcknowledged();
        }

    }

    @Override
    public boolean recordExists() {
        return false;
    }

    @Override
    public boolean passwordIsValid(String password) {
        return false;
    }

    @Override
    public Settings getRecord() {
        return null;
    }
}
