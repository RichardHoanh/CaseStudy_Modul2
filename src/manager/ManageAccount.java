package manager;

import io.ReadAndWriteAccount;
import io.ReadAndWriteCart;
import model.Account;
import model.Product;
import model.User;

import java.util.*;

public class ManageAccount {
    static public ArrayList<Account> accounts = ReadAndWriteAccount.read();
    static Scanner scanner = new Scanner(System.in);
    static HashMap<String, ArrayList<Product>> allCart = ReadAndWriteCart.read();
    static public String CurrentAccount;

    public static void login() {
        String username, password;
        System.out.println("Nhập tên đăng nhập");
        CurrentAccount = username = scanner.nextLine();
        System.out.println("Nhập mật khẩu");
        password = scanner.nextLine();
        if (username.equals("Hoanh") && password.equals("1102")) {
            System.out.println("Đăng nhập bằng tài khoản Admin");
            mainAdmin();
        } else {
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getUserName().equals(username) && accounts.get(i).getPassWord().equals(password)) {
                    System.out.println("Đăng nhập bằng tài khoản User");

                    if (allCart.get(CurrentAccount) == null) {
                        allCart.put(CurrentAccount, new ArrayList<Product>());
                        ReadAndWriteCart.write(allCart);
                    }
                    new ManageAccount().mainUser();
                    break;
                }

            }
            System.out.println("Đăng nhập thất bại");
        }
        scanner.nextLine();

    }

    private static void setupCart(String username) {

    }

    public static Account createAcc() {
        String userName;
        String passWord;
        System.out.println("Nhập tên đăng nhập");
        userName = scanner.nextLine();
        if (accounts.contains(userName)) {
            System.out.println("Tên đăng nhập đã tồn tại, vui lòng nhập tên mới");
            userName = scanner.nextLine();
        }
        System.out.println("Nhập mật khẩu");
        passWord = scanner.nextLine();
        if (passWord.length() < 6) {
            System.out.println("Mật khẩu phải trên 6 kí tự ");
            System.out.println("Nhập lại mật khẩu");
            passWord = scanner.nextLine();
        }
        System.out.println("Nhập tên");
        String name = scanner.nextLine();
        System.out.println("Nhập giới tính");
        String gender = scanner.nextLine();
        int age;
        do {
            try {
                System.out.println("Nhập tuổi");
                age = Integer.parseInt(scanner.nextLine());
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Tuổi phải là số!");
            }
        } while (true);
        System.out.println("Đăng kí thành công");
        return new User(userName, passWord, name, gender, age);
    }

    public static void addAccount() {
        Account acc = createAcc();
        accounts.add(acc);
        ReadAndWriteAccount.write(accounts);
        scanner.nextLine();
    }

    public static int findIndexByUsername() {
        System.out.println("Nhập tên đăng nhập");
        String userName = scanner.nextLine();

        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account.getUserName().equals(userName)) {
                return i;
            }
        }

        return -1;
    }

    public static void findAccByIndex() {
        int index = findIndexByUsername();
        if (index==-1){
            System.out.println("Không tìm thấy tài khoản");
        }else {
            System.out.println("Tìm thấy tài khoản có thông tin như sau: " + accounts.get(index).toString());
        }
        scanner.nextLine();

    }

    public static void deleteAccount() {
        int index = findIndexByUsername();
        accounts.remove(index);
        ReadAndWriteAccount.write(accounts);
        scanner.nextLine();
    }


    public static void editAccount() {
        int index = findIndexByUsername();
        Account acc = createAcc();
        accounts.set(index, acc);
        ReadAndWriteAccount.write(accounts);
        scanner.nextLine();
    }

    public static void mainAdmin() {

        System.out.println("Chào bạn đây là phần dành cho quản trị viên");
        Scanner scanner = new Scanner(System.in);
        ReadAndWriteAccount.write(ManageAccount.accounts);
        while (true) {
            System.out.println("<----------MENU-QUẢN TRỊ VIÊN---------->");
            System.out.println("""
                    1. Thêm tài khoản
                    2. Tìm kiếm tài khoản
                    3. Xóa tài khoản
                    4. Thêm sản phẩm vào danh sách
                    5. Tìm kiếm sản phẩm
                    6. Sửa thông tin sản phẩm
                    7. Xóa sản phẩm khỏi danh sách                                   
                    8. Hiển thị danh sách sản phẩm                                        
                    9. Thoát""");
            int choice;
            do {
                try {
                    System.out.print("Mời nhập lựa chọn: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Lựa chọn phải là số!");
                }
            } while (true);
            switch (choice) {
                case 1:
                    addAccount();
                    break;
                case 2:
                    findAccByIndex();
                    break;
                case 3:
                    deleteAccount();
                    break;
                case 4:
                    ManageProduct.addProduct();
                    break;
                case 5:
                    ManageProduct.findProductById();
                    break;
                case 6:
                    ManageProduct.editProduct();

                    break;
                case 7:
                    ManageProduct.deleteProduct();
                    break;
                case 8:
                    ManageProduct.showProduct();
                    break;
                case 9:
                    ReadAndWriteAccount.write(ManageAccount.accounts);
                    return;
            }
        }
    }

    public void mainUser() {
        ManageShoppingCart currentCart = new ManageShoppingCart(allCart.get(CurrentAccount));

        System.out.println("Trang quản lý dành cho khách hàng");
        Scanner scanner = new Scanner(System.in);
        ReadAndWriteAccount.write(ManageAccount.accounts);
        while (true) {
            System.out.println("<----------MENU-USER---------->");
            System.out.println("""
                    1. Sửa thông tin tài khoản
                    2. Xem danh sách sản phẩm
                    3. Thêm sản phẩm vào giỏ hàng
                    4. Xóa sản phẩm khỏi giỏ hàng
                    5. Hiển thị hóa đơn
                    6. Hiển thị top 3 sản phẩm có giá cao nhất                                   
                    7. Thoát""");
            int choice;
            do {
                try {
                    System.out.print("Mời nhập lựa chọn: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Lựa chọn phải là số!");
                }
            } while (true);
            switch (choice) {
                case 1:
                    editAccount();
                    break;
                case 2:
                    ManageProduct.showProduct();
                    break;
                case 3:
                    currentCart.addProductIntoCart();
                    break;
                case 4:
                    currentCart.deleteProductCart();
                    break;
                case 5:
                    currentCart.showBill();
                    break;
                case 6:
                    ManageProduct.showTop3Price();
                    break;
                case 7:
                    ReadAndWriteAccount.write(ManageAccount.accounts);
                    return;
            }
        }

    }


}
