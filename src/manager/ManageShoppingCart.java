package manager;

import io.ReadAndWriteCart;
import io.ReadAndWriteProduct;
import model.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageShoppingCart implements Serializable {
    public ArrayList<Product> carts;
    Scanner scanner = new Scanner(System.in);

    public ManageShoppingCart(ArrayList<Product> carts) {
        this.carts = carts;
    }

    public int findIndexAndShowProduct() {
        System.out.println("Nhập tên sản phẩm");
        String name = scanner.nextLine();
        for (int i = 0; i < ManageProduct.products.size(); i++) {
            if (ManageProduct.products.get(i).getName().equals(name)) {
                System.out.println("Sản phẩm bạn muốn tìm có thông tin như sau: ");
                System.out.printf("%-25s%-25s%-25s%-25s\n", "ID", "Name", "Amount", "Price");
                System.out.printf("%-25d%-25s%-25s%-25f\n", ManageProduct.products.get(i).getId(), ManageProduct.products.get(i).getName(), ManageProduct.products.get(i).getAmount(), ManageProduct.products.get(i).getPrice());
                return i;
            }
        }
        System.out.println("Không tìm thấy sản phẩm trong danh sách");
        return -1;
    }

    public void addProductIntoCart() {
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

        ManageAccount.allCart.put(ManageAccount.CurrentAccount, this.carts);
        ReadAndWriteCart.write(ManageAccount.allCart);
        ReadAndWriteProduct.write(ManageProduct.products);
        scanner.nextLine();
    }

    public int findIndexDelete() {
        int index = 0;
        System.out.println("Nhập tên sản phẩm muốn xóa khỏi giỏ hàng");
        String name=scanner.nextLine();
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getName().equals(name)) {
                return index = i;
            }
        }
        System.out.println("Sản phẩm không có trong giỏ hàng");
        return -1;
    }

    public void deleteProductCart() {

        int index = findIndexDelete();
        if (index == -1) {
            return;
        }
        carts.remove(index);
        ReadAndWriteCart.write(ManageAccount.allCart);

        scanner.nextLine();

    }


    public void showBill() {
        System.out.println("Giỏ hàng của bạn gồm những sản phẩm sau: ");
        Double money = 0.0;
        Double totalMoney = 0.0;
        System.out.printf("%-25s%-25s%-25s%-25s\n", "ID", "Name", "Amount", "Price");
        for (int i = 0; i < carts.size(); i++) {
            money = carts.get(i).getAmount() * carts.get(i).getPrice();
            System.out.printf("%-25d%-25s%-25s%-25f\n", carts.get(i).getId(), carts.get(i).getName(), carts.get(i).getAmount(),carts.get(i).getPrice());
            totalMoney+=money;

        }
        System.out.println("Tổng số tiền quý khách phải thanh toán là: " + totalMoney + " đồng");
        scanner.nextLine();

    }

    public ArrayList<Product> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<Product> carts) {
        this.carts = carts;
    }

}
