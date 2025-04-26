package br.com.tacontigo;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "users")
public class User extends PanacheMongoEntity {
    public String fullName;
    public String userType;
    public String registrationDate;
    public String gender;
    public String race;
    public String nationality;
}