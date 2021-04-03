package by.issoft.serializator;

import by.issoft.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Objects;

public class SerializatorOrders {
    private static final Logger logger = LoggerFactory.getLogger(SerializatorOrders.class);

    public static boolean serialization(Order order) {
        boolean flag = false;
        String filename = order.getOrderId().toString() + ".txt";
        File file = new File("./src/main/resources/orders_data/" + filename);
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream(file);
            if (fos != null) {
                oos = new ObjectOutputStream(fos);
                oos.writeObject(order);
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

    public static Order deserialization(String path) {
        File file = new File(path);
        ObjectInputStream ois = null;
        Order order = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            if (fis != null) {
                ois = new ObjectInputStream(fis);
                order = (Order) Objects.requireNonNull(ois).readObject();
                logger.debug("Load order " + order.getOrderId());
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.error(e.toString());
        }
        return order;
    }
}
