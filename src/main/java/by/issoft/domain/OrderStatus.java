package by.issoft.domain;

public enum OrderStatus {
    ACCEPT("Accept"),
    PENDING("Pending"),
    DELAYED("Delayed"),
    COMPLETED("Completed"),
    DECLINED("Declined");

    private final String title;

    OrderStatus(String title){
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
