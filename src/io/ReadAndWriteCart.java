package io;

import model.Product;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReadAndWriteCart {
    public static void write(ArrayList<Product> cart) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("cart.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(cart);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Product> read() {
        try {
            FileInputStream fileInputStream = new FileInputStream("cart.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (ArrayList<Product>) objectInputStream.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
