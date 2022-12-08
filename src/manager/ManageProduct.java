package manager;

import io.ReadAndWriteProduct;
import model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageProduct {

    static public ArrayList<Product> products = ReadAndWriteProduct.read();
    static Scanner scanner = new Scanner(System.in);

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static void setProducts(ArrayList<Product> products) {
        ManageProduct.products = products;
    }

    public static Product createProduct() {
        int id, amount;
        String name;
        double price;
        do {
            try {
                System.out.println("Nhập id sản phẩm");
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("id phải là số!");
            }
        } while (true);
        System.out.println("Nhập tên sản phẩm");
        name = scanner.nextLine();

        do {
            try {
                System.out.println("Nhập số lượng sản phẩm");
                amount = Integer.parseInt(scanner.nextLine());
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Phải nhập số!");
            }
        } while (true);

        do {
            try {
                System.out.println("Nhập giá sản phẩm");
                price = Double.parseDouble(scanner.nextLine());
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Giá sản phẩm phải là số!");
            }
        } while (true);
        return new Product(id, name, amount, price);
    }

    public static void addProduct() {
        Product sp = createProduct();
        products.add(sp);
        ReadAndWriteProduct.write(products);
        scanner.nextLine();
    }

    public static int findIndexById() {
        System.out.println("Nhập id sản phẩm");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < products.size(); i++) {
            Product sp = products.get(i);
            if (sp.getId() == id) {
                System.out.println("Sản phẩm có id là " + id + " được tìm thấy có thông tin như sau: " +
                        sp.toString());
                return i;
            }
        }
        System.out.println("Không tìm thấy sản phẩm");
        return -1;
    }

    public static void findProductById() {
        int index = findIndexById();
        products.get(index).toString();
        scanner.nextLine();
    }

    public static void deleteProduct() {
        int index = findIndexById();
        products.remove(index);
        ReadAndWriteProduct.write(products);
        scanner.nextLine();
    }

    public static void editProduct() {
        int index = findIndexById();
        Product sp = createProduct();
        products.set(index, sp);
        ReadAndWriteProduct.write(products);
        scanner.nextLine();
    }

    public static void showProduct() {
        System.out.println("Danh sách sản phẩm như sau: ");
        System.out.printf("%-25s%-25s%-25s%-25s\n", "ID", "Name", "Amount", "Price");

        for (int i = 0; i < products.size(); i++) {
            System.out.printf("%-25d%-25s%-25s%-25f\n", products.get(i).getId(), products.get(i).getName(), products.get(i).getAmount(), products.get(i).getPrice());
        }
        scanner.nextLine();
    }

    public static void showTop3Price() {
        Collections.sort(products, new SortByPrice());
        if (products.size() > 3) {
            System.out.println("Top 3 sản phẩm có giá cao nhất như sau: ");
            System.out.printf("%-25s%-25s%-25s%-25s\n", "ID", "Name", "Amount", "Price");
            for (int i = 0; i < 3; i++) {
                System.out.printf("%-25d%-25s%-25s%-25f\n", products.get(i).getId(), products.get(i).getName(), products.get(i).getAmount(), products.get(i).getPrice());
            }
        } else {
            showProduct();
        }
        scanner.nextLine();
    }

}

