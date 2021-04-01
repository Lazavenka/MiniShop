package by.issoft.domain;

import java.util.UUID;

public class OrderItem {
    private final int cost;
    private int count;
    private final String name;
    private final UUID itemID;

    public OrderItem(int cost, int count, String name) {
        this.cost = cost;
        this.count = count;
        this.name = name;
        this.itemID = UUID.randomUUID();
    }

    public UUID getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public int getTotalCost() {
        return cost * count;
    }
    public void setCount(int count){
        this.count = count;
    }
    @Override
    public String toString() {
        return "Order item ID: "+ itemID.toString() + " -> " + name + "." +
                " Count " + count + ", cost " + cost + "." +
                " Total cost - "+ getTotalCost();
    }
}
