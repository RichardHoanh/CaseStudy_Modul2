package io;

import model.Product;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReadAndWriteProduct{
    public static void write(ArrayList<Product>products){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("product.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(products);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<Product> read(){
        try {
            FileInputStream fileInputStream = new FileInputStream("product.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (ArrayList<Product>) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}