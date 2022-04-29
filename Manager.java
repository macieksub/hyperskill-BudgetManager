package budget;

import java.io.*;
import java.util.*;

public class Manager {

//    private final List<String> purchaseName = new ArrayList<>();
//    Można zrobić array of list


    private final Map<Integer, List<String>> purchaseTypes= Map.of(
            1, new ArrayList<>(), // Food
            2, new ArrayList<>(),  // Clothes
            3, new ArrayList<>(), // Entertainment
            4, new ArrayList<>()); // Other
    private double income;
    private double price;
    private double balance;
//    final File file = new File("C:\\Users\\macie\\IdeaProjects\\Budget Manager\\Budget Manager\\task\\src\\budget\\purchases.txt");
    final File file = new File("purchases.txt");

    double getPrice() {
        return price;
    }

    void addIncome(double income) {
        this.income += income;
        this.balance += income;
        System.out.println("Income was added!\n");
    }

//    private String toName(int type) {
//        switch (type) {
//            case 1:
//                return "Food";
//            case 2:
//                return "Clothes";
//            case 3:
//                return "Entertainment";
//            case 4:
//                return "Other";
//            default:
//                return "";
//        }
//    }

    void addPurchase(int type, String name, double price) {
        purchaseTypes.get(type).add(name + " $" + String.format("%.2f", price));
        this.price += price;
        this.balance -= price;
        System.out.println("Purchase was added!");
    }

    void printList(int type) {
        switch (type) {
            case 1:
                System.out.println("\nFood:");
                break;
            case 2:
                System.out.println("\nClothes:");
                break;
            case 3:
                System.out.println("\nEntertainment:");
                break;
            case 4:
                System.out.println("\nOther:");
                break;
            case 5:
                System.out.println("\nAll:");
                break;
        }
        if (type == 5) {
            for (int i = 1; i <= 4; i++) {
                for (String a : purchaseTypes.get(i)) {
                    System.out.println(a);
                }
            }
            System.out.printf("Total sum: $%.2f", price);
        } else {
            if (purchaseTypes.get(type).isEmpty()) {
                System.out.println("The purchase list is empty!");
            } else {
                double price2 = 0;
                for (String a : purchaseTypes.get(type)) {
                    System.out.println(a);
                    price2 += Double.parseDouble(a.substring(a.lastIndexOf('$') + 1));
                }
                System.out.printf("Total sum: $%.2f", price2);
            }
        }
    }

    void printBalance() {
        if (balance <= 0) {
            System.out.println("\nBalance: $0.00\n");
        } else  {
            System.out.printf("\nBalance: $%.2f\n\n", balance);
        }
    }

    static void printMenu() {
//        System.out.println("""
//            Choose your action:
//            1) Add income
//            2) Add purchase
//            3) Show list of purchases
//            4) Balance
//            0) Exit
//            """);
        System.out.println("Choose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save\n" +
                "6) Load\n" +
                "7) Analyze (Sort)\n" +
                "0) Exit");
    }

    static  void printPurchaseMenu() {
        System.out.println("\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) Back");
    }

    static  void printPurchaseListMenu() {
        System.out.println("\nChoose the type of purchases\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) All\n" +
                "6) Back");
    }

    static void printSortMenu() {
        System.out.println("\nHow do you want to sort?\n" +
                "1) Sort all purchases\n" +
                "2) Sort by type\n" +
                "3) Sort certain type\n" +
                "4) Back");
    }

    static  void printSortCertainMenu() {
        System.out.println("\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other");
    }

    void save() {
        try (PrintWriter writer = new PrintWriter(file)) {
            if (balance <= 0) {
                writer.println("0.0");
            } else  {
//                writer.println(String.valueOf(balance).replace('.', ','));
                writer.println(balance);
            }
//            writer.println(String.valueOf(income).replace('.', ','));
//            writer.println(String.valueOf(price).replace('.', ','));
            writer.println(income);
            writer.println(price);
            for (int i = 1; i <= 4; i++) {
                writer.println(i);
                for (String a : purchaseTypes.get(i)) {
                    writer.println(a);
                }
            }
            System.out.println("\nPurchases were saved!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void load() {
        try (Scanner sc = new Scanner(file).useLocale(Locale.US)){
            for (var a : purchaseTypes.values()) {
                a.clear();
            }
            int aa = 0;
//            try {
                if (sc.hasNext()) {
                    balance = sc.nextDouble();
                }
                if (sc.hasNext()) {
                    income = sc.nextDouble();
                    price = sc.nextDouble();
                    aa = sc.nextInt();
                    sc.nextLine();
                }
//            } catch (Exception e) {
//                if (sc.hasNext()) {
////                    income = Double.parseDouble(sc.nextLine().trim().replace('.', ','));
//                    String bb = sc.nextLine().replace('.', ',');
//                    System.out.println(bb);
//                    double cc = Double.parseDouble(bb);
//                    System.out.println(cc);
////                    income = sc.nextDouble();
//                }
//                if (sc.hasNext()) {
//                    System.out.println(sc.nextLine().replace('.', ','));
//                    System.out.println(sc.nextLine().replace('.', ','));
////                    balance = Double.parseDouble(sc.nextLine().trim().replace('.', ','));
////                    balance = sc.nextDouble();
////                    price = Double.parseDouble(sc.nextLine().trim().replace('.', ','));
////                    price = sc.nextDouble();
//                    aa = sc.nextInt();
//                    sc.nextLine();
//                }
//            }
            while (sc.hasNext()) {
                String bb = sc.nextLine();
                try {
                    aa = Integer.parseInt(bb);
                } catch (Exception e) {
                    purchaseTypes.get(aa).add(bb);
                }
            }
            System.out.println("\nPurchases were loaded!\n");
        } catch (Exception e) {
            System.out.println("\nCan't read file!\n");
        }
    }

    void sortAll() {
        if (getPrice() == 0) {
            System.out.println("\nThe purchase list is empty!");
        } else {
            int size = 0;
            for (var a : purchaseTypes.values()) {
                size += a.size();
            }
            String[] sort = new String[size];
            int j = 0;
            for (int i = 1; i <= 4; i++) {
                for (String a : purchaseTypes.get(i)) {
                    sort[j] = a;
                    j++;
                }
            }
            Arrays.sort(sort, (a, b) -> Double.compare(Double.parseDouble(b.substring(b.lastIndexOf('$') + 1)), Double.parseDouble(a.substring(a.lastIndexOf('$') + 1))));
            System.out.println("\nAll:");
            for (String a : sort) {
                System.out.println(a);
            }
            System.out.printf("Total sum: $%.2f\n", price);
        }
    }

    void sortByType() {
//        SortedMap<Double, Integer> sorted = new TreeMap<>();
        double[][] sorted2 = new double[4][2];
        double price2;
        for (int i = 1; i <= 4; i++) {
            price2 = 0;
//            for (String a : purchaseTypes.get(i)) {
//                price2 += Double.parseDouble(a.substring(a.lastIndexOf('$') + 1));
//            }
//            sorted.put(price2, i);
            for (String a : purchaseTypes.get(i)) {
                price2 += Double.parseDouble(a.substring(a.lastIndexOf('$') + 1));
            }
            sorted2[i - 1][0] = price2;
            sorted2[i - 1][1] = i;
        }
        System.out.println("\nTypes:");
//        for (var entry : sorted.entrySet()) {
//            switch (entry.getValue()) {
//                case 1:
//                    System.out.print("Food - $");
//                    break;
//                case 2:
//                    System.out.print("Clothes - $");
//                    break;
//                case 3:
//                    System.out.print("Entertainment - $");
//                    break;
//                case 4:
//                    System.out.print("Other - $");
//                    break;
//            }
//            System.out.printf("%.2f\n", entry.getKey());
//        }
        Arrays.sort(sorted2, (a, b) -> Double.compare(b[0], a[0]));
        for (double[] entry : sorted2) {
            switch ((int) entry[1]) {
                case 1:
                    System.out.print("Food - $");
                    break;
                case 2:
                    System.out.print("Clothes - $");
                    break;
                case 3:
                    System.out.print("Entertainment - $");
                    break;
                case 4:
                    System.out.print("Other - $");
                    break;
            }
            System.out.printf("%.2f\n", entry[0]);
        }
        System.out.printf("Total sum: $%.2f\n", price);
    }

    void sortCertainType(int type) {
        if (purchaseTypes.get(type).isEmpty()) {
            System.out.println("\nThe purchase list is empty!");
        } else {
            int size = purchaseTypes.get(type).size();
            String[] sort = new String[size];
            int j = 0;
            for (String a : purchaseTypes.get(type)) {
                sort[j] = a;
                j++;
            }
            Arrays.sort(sort, (a, b) -> Double.compare(Double.parseDouble(b.substring(b.lastIndexOf('$') + 1)), Double.parseDouble(a.substring(a.lastIndexOf('$') + 1))));
            switch (type) {
                case 1:
                    System.out.println("\nFood:");
                    break;
                case 2:
                    System.out.println("\nClothes:");
                    break;
                case 3:
                    System.out.println("\nEntertainment:");
                    break;
                case 4:
                    System.out.println("\nOther:");
                    break;
            }
            for (String a : sort) {
                System.out.println(a);
            }
            System.out.printf("Total sum: $%.2f\n", price);
        }
    }
}
