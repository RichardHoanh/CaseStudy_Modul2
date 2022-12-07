package manager;

import io.ReadAndWriteCart;
import io.ReadAndWriteProduct;
import model.Product;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageShoppingCart {
    static public ArrayList<Product> carts = ReadAndWriteCart.read();
    static Scanner scanner = new Scanner(System.in);

    public static int findIndexAndShowProduct() {
        System.out.println("Nhập tên sản phẩm bạn muốn thêm vào giỏ hàng");
        String name = scanner.nextLine();
        for (int i = 0; i < ManageProduct.products.size(); i++) {
            if (ManageProduct.products.get(i).getName().equals(name)) {
                System.out.println("Sản phẩm bạn muốn thêm có thông tin như sau: ");
                System.out.printf("%-25s%-25s%-25s%-25s\n","ID","Name","Amount","Price");
                System.out.printf("%-25d%-25s%-25s%-25f\n",ManageProduct.products.get(i).getId(),ManageProduct.products.get(i).getName(),ManageProduct.products.get(i).getAmount(),ManageProduct.products.get(i).getPrice());

                return i;
            }
        }
        System.out.println("Không tìm thấy sản phẩm trong danh sách");
        return -1;
    }

    public static void addProductIntoCart() {
        int index = findIndexAndShowProduct();
        if (index == -1) {
            return;
        }
        int number = 0;
        do {
            try {
                System.out.println("Nhập số lượng sản phẩm bạn muốn thêm vào giỏ hàng");
                number = Integer.parseInt(scanner.nextLine());
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Phải nhập số !");
            }
        } while (true);

        int amount = ManageProduct.products.get(index).getAmount();
        if (number > amount) {
            System.out.println("Không đủ số lượng");
            return;

        } else {
            Product tmp = new Product(ManageProduct.products.get(index));
            tmp.setAmount(number);
            carts.add(tmp);
            ManageProduct.products.get(index).setAmount(amount - number);
        }
        System.out.println("Bạn đã thêm thành công sản phẩm vào giỏ hàng");
        ReadAndWriteCart.write(carts);
        ReadAndWriteProduct.write(ManageProduct.products);
        scanner.nextLine();
    }

    public static void deleteProductCart() {
        int index = findIndexAndShowProduct();
        if (index == -1) {
            return;
        }
        carts.remove(index);
        ReadAndWriteCart.write(carts);
        scanner.nextLine();

    }


    public static void showBill() {
        System.out.println("Giỏ hàng của bạn gồm những sản phẩm sau: ");
        Double moneyBill = 0.0;
        System.out.printf("%-25s%-25s%-25s%-25s\n","ID","Name","Amount","Price");
        for (int i = 0; i < carts.size(); i++) {
            System.out.printf("%-25d%-25s%-25s%-25f\n",carts.get(i).getId(),carts.get(i).getName(),carts.get(i).getAmount(),carts.get(i).getPrice());

            moneyBill = carts.get(i).getAmount() * carts.get(i).getPrice();
        }
        System.out.println("Tổng số tiền quý khách phải thanh toán là: "+ moneyBill+" đồng");
        scanner.nextLine();

    }


}
