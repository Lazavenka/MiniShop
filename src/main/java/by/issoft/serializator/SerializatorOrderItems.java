package by.issoft.serializator;

import by.issoft.domain.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Objects;

public class SerializatorOrderItems {
    private static final Logger logger = LoggerFactory.getLogger(SerializatorOrders.class);

    public static boolean serialization(OrderItem orderItem) {
        boolean flag = false;
        final String filename = orderItem.getItemID().toString() + ".txt";
        File file = new File("./src/main/resources/orderItems_data/" + filename);
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream(file);
            if (fos != null) {
                oos = new ObjectOutputStream(fos);
                oos.writeObject(orderItem);
                logger.debug("Order successfully saved to file " + filename);
                flag = true;
            }

        } catch (IOException e) {
            logger.error(e.toString());
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    logger.error(e.toString());
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    public static OrderItem deserialization(String path) {
        File file = new File(path);
        ObjectInputStream ois = null;
        OrderItem orderItem = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            if (fis != null) {
                ois = new ObjectInputStream(fis);
                orderItem = (OrderItem) Objects.requireNonNull(ois).readObject();
                logger.debug("Load order " + orderItem.getItemID());
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.error(e.toString());
        }
        return orderItem;
    }
}
