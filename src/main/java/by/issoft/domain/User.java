package by.issoft.domain;

import java.util.List;
import java.util.UUID;

public class User {
    private final UUID userID;
    private final String firstName;
    private final String lastName;

    private int balance;
    private List<UUID> orderIDs;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = UUID.randomUUID();
    }

    public User(String firstName, String lastName, UUID userID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
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

    public String getLastName() {
        return lastName;
    }

    public List<UUID> getOrderIDs() {
        return orderIDs;
    }

    @Override
    public String toString() {
        return "User id: " + userID + " -> " + firstName + " " + lastName + ". Balance: " + balance;
    }
}
