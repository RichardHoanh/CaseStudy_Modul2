import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageAccount {
    static ArrayList<Account> accounts = ReadAndWriteAccount.read();
    static Scanner scanner = new Scanner(System.in);


    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    public static void setAccounts(ArrayList<Account> accounts) {
        ManageAccount.accounts = accounts;
    }


    public static void login() {
        String username, password;
        System.out.println("Nhập tên đăng nhập");
        username = scanner.nextLine();
        System.out.println("Nhập mật khẩu");
        password = scanner.nextLine();
        if (username.equals("Hoanh") && password.equals("1102")) {
            System.out.println("Đăng nhập bằng tài khoản Admin");
            mainAdmin();
        } else {
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getUserName().equals(username) && accounts.get(i).getPassWord().equals(password)) {
                    System.out.println("Đăng nhập bằng tài khoản User");
                    break;
                } else {
                    System.out.println("Đăng nhập thất bại");
                    break;
                }
            }
        }

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
        System.out.println("Nhập tuổi");
        String age = scanner.nextLine();
        System.out.println("Đăng kí thành công");
        return new User(userName, passWord, name, gender, age);
    }

    public static void addAccount() {
        Account acc = createAcc();
        accounts.add(acc);
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
        System.out.println("Không tìm thấy tài khoản");
        return -1;
    }

    public static void findAccByIndex() {
        int index = findIndexByUsername();
        System.out.println("Tìm thấy tài khoản có thông tin như sau: " + accounts.get(index).toString());
    }

    public static void deleteAccount() {
        int index = findIndexByUsername();
        accounts.remove(index);
        ReadAndWriteAccount.write(accounts);

    }

    public static void showAcc() {
        for (int i = 0; i < accounts.size(); i++) {
            accounts.get(i).toString();

        }
    }

    public static void mainAdmin() {

        System.out.println("Chào bạn đây là phần dành cho quản trị viên");
        Scanner scanner = new Scanner(System.in);
        ReadAndWriteAccount.write(ManageAccount.getAccounts());
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
                    9. Thoát
                    10. Hiển thị tài khoản""");
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
                case 10:
                    showAcc();
                    break;
                case 9:
                    ReadAndWriteAccount.write(ManageAccount.getAccounts());
                    System.exit(0);
            }
        }

    }


    public static void mainUser() {

        System.out.println("Trang quản lý dành cho khách hàng");
        Scanner scanner = new Scanner(System.in);
        ReadAndWriteAccount.write(ManageAccount.getAccounts());
        while (true) {
            System.out.println("<----------MENU-USER---------->");
            System.out.println("""
                    1. Xem danh sách sản phẩm
                    2. Tìm kiếm tài khoản
                    3. Xóa tài khoản
                    4. Thêm sản phẩm vào danh sách
                    5. Tìm kiếm sản phẩm
                    6. Sửa thông tin sản phẩm
                    7. Xóa sản phẩm khỏi danh sách                                   
                    8. Hiển thị danh sách sản phẩm                                        
                    9. Thoát
                    10. Hiển thị tài khoản""");
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
                case 10:

                    showAcc();

                    break;
                case 9:
                    ReadAndWriteAccount.write(ManageAccount.getAccounts());
                    System.exit(0);
            }
        }

    }
}
