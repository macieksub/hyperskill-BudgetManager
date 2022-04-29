package budget;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        Manager menager = new Manager();
        int action = 5;
        int type;
        while (action != 0) {
            Manager.printMenu();
            action = sc.nextInt();
            sc.nextLine();
            switch (action) {
                case 0:
                    break;
                case 1:
                    System.out.println("\nEnter income:");
                    menager.addIncome(sc.nextDouble());
                    break;
                case 2:
                    type = 0;
                    while (type != 5) {
                        Manager.printPurchaseMenu();
                        type = sc.nextInt();
                        sc.nextLine();
                        if (type != 5) {
                            System.out.println("\nEnter purchase name:");
                            String name = sc.nextLine();
                            System.out.println("Enter its price:");
                            menager.addPurchase(type, name, sc.nextDouble());
                            sc.nextLine();
                        }
                    }
                    System.out.println();
                    break;
                case 3:
                    if (menager.getPrice() == 0) {
                        System.out.println("\nThe purchase list is empty!\n");
                    } else {
                        type = 0;
                        while (type != 6) {
                            Manager.printPurchaseListMenu();
                            type = sc.nextInt();
                            sc.nextLine();
                            if (type != 6) {
                                menager.printList(type);
                            }
                            System.out.println();
                        }
                    }
                    break;
                case 4:
                    menager.printBalance();
                    break;
                case 5:
                    menager.save();
                    break;
                case 6:
                    menager.load();
                    break;
                case 7:
                    type = 0;
                    while (type != 4) {
                        Manager.printSortMenu();
                        type = sc.nextInt();
                        sc.nextLine();
                        if (type == 1) {
                            menager.sortAll();
                        } else if (type == 2) {
                            menager.sortByType();
                        } else if (type == 3) {
                            type = 0;
                            Manager.printSortCertainMenu();
                            type = sc.nextInt();
                            sc.nextLine();
                            menager.sortCertainType(type);
                            type = 3;
                        }
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("Wrong number!\n");
            }
        }
        System.out.println("\nBye!");
    }
}
