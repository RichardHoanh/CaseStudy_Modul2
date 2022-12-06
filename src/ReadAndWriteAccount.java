import java.io.*;
import java.util.ArrayList;

public class ReadAndWriteAccount {
    public static void write(ArrayList<Account> accounts) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("account.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(accounts);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Account> read() {
        try {
            FileInputStream fileInputStream = new FileInputStream("account.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (ArrayList<Account>) objectInputStream.readObject();
        } catch (Exception e) {

            return new ArrayList<>();
        }
    }

}

