

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainAccount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReadAndWriteAccount.write(ManageAccount.getAccounts());
        while (true) {
            System.out.println("<----------MENU---------->");
            System.out.println("""
                    1. Đăng nhập
                    2. Đăng kí             
                    3. Thoát""");
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
                    ManageAccount.login();
                    break;
                case 2:
                    ManageAccount.addAccount();
                    ReadAndWriteAccount.write(ManageAccount.getAccounts());
                    break;

                case 3:
                    ReadAndWriteAccount.write(ManageAccount.getAccounts());
                    System.exit(0);
            }
        }
    }


}

