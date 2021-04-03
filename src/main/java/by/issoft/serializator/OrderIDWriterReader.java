package by.issoft.serializator;

import by.issoft.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderIDWriterReader {
    private static final Logger logger = LoggerFactory.getLogger(SerializatorOrders.class);

    public static boolean writeNewOrderIDToFile(Order order) {
        FileOutputStream fos = null;
        boolean success = false;
        String id = order.getOrderId().toString()+"\n";
        byte[] idBytes;
        try {
            fos = new FileOutputStream("./src/main/resources/OrderIDs.txt", true);
            idBytes = id.getBytes();
            fos.write(idBytes);
            success = true;
            logger.debug("Successfully write " +id + " to file.");
        } catch (IOException e) {
            logger.debug("UnsupportedEncodingException." + e.toString());
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                logger.debug("UnsupportedEncodingException. " + e.toString());
            }
        }
        return success;
    }
    public static List<String> readAllIDs(){
        List<String> orderIDs = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/OrderIDs.txt"));
            orderIDs = bufferedReader.lines().collect(Collectors.toList());
            logger.debug("Load "+orderIDs.size()+" entries.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return orderIDs;
    }
}
