package com.foodie.model;

import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import java.util.ArrayList;
import java.util.List;

@PersistenceCapable
public class User {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key userId;

    @Persistent
    private String userName;

    @Persistent
    private String password;

    @Persistent
    private String email;

    @Persistent
    private String phoneNO;

    @Persistent
    private String name;

    @Persistent
    private byte[] logo;

    @Persistent
    private List<Location> deliveryAddresses = new ArrayList<Location>();

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, String email, String phoneNO, String name) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNO = phoneNO;
        this.name = name;
        this.logo = new byte[0];
    }

    public Key getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // TODO: password should be encrypted with a hash function
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNO() {
        return phoneNO;
    }

    public void setPhoneNO(String phoneNO) {
        this.phoneNO = phoneNO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo.clone();
    }

    public List<Location> getDeliveryAddresses() {
        return deliveryAddresses;
    }

    // TODO: need validation
    public void addNewAddress(Location address) {
        if (address != null) {
            deliveryAddresses.add(address);
        }

    }
}
