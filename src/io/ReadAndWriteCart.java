package io;

import manager.ManageShoppingCart;
import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadAndWriteCart implements Serializable {
    public static void write(HashMap<String, ArrayList<Product>> cart) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("cart.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(cart);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static HashMap<String, ArrayList<Product>> read() {
        try {
            FileInputStream fileInputStream = new FileInputStream("cart.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (HashMap<String, ArrayList<Product>>) objectInputStream.readObject();
        } catch (Exception e) {
            return new HashMap<String, ArrayList<Product>>();
        }
    }
}
