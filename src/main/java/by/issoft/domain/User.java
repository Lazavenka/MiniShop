package by.issoft.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User implements Serializable {
    private final UUID userID;
    private String firstName;
    private final String lastName;

    private int balance;
    private List<UUID> orderIDs;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = UUID.randomUUID();
        this.orderIDs = new ArrayList<>();
    }

    public User(String firstName, String lastName, UUID userID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.orderIDs = new ArrayList<>();
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public UUID getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public List<UUID> getOrderIDs() {
        return orderIDs;
    }

    public void setOrderIDs(List<UUID> orderIDs) {
        this.orderIDs = orderIDs;
    }
    @Override
    public String toString() {
        return "User id: " + userID + " -> " + firstName + " " + lastName + ". Balance: " + balance;
    }
}
