import java.io.*;
import java.util.*;

public class ContactManager {

    static List<String> names = new ArrayList<>();
    static List<String> numbers = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "contacts.txt";

    public static void main(String[] args) {
        loadContacts(); 
        while (true) {
            System.out.println("\n======= CONTACT MANAGER =======");
            System.out.println("1. Add Contact");
            System.out.println("2. Delete Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Sort & Display All");
            System.out.println("5. Save & Exit");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine();
            switch (choice) {
                case "1": addContact(); break;
                case "2": deleteContact(); break;
                case "3": searchContact(); break;
                case "4": sortAndDisplay(); break;
                case "5": saveContacts(); System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void addContact() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Number: ");
        String number = sc.nextLine().trim();

        if (name.isEmpty() || number.isEmpty()) {
            System.out.println("Name/Number cannot be empty.");
            return;
        }

        // Check duplicates
        if (names.contains(name)) {
            System.out.println("Contact already exists!");
            return;
        }

        names.add(name);
        numbers.add(number);

        System.out.println("Contact added.");
    }

    static void deleteContact() {
        System.out.print("Enter Name to Delete: ");
        String name = sc.nextLine().trim();

        int index = names.indexOf(name);
        if (index == -1) {
            System.out.println("Contact not found.");
            return;
        }

        names.remove(index);
        numbers.remove(index);

        System.out.println("Contact deleted.");
    }

    static void searchContact() {
        System.out.print("Enter Name to Search: ");
        String name = sc.nextLine();

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(name)) {
                System.out.println("FOUND: " + names.get(i) + " - " + numbers.get(i));
                return;
            }
        }

        System.out.println("Contact not found.");
    }

    static void sortAndDisplay() {
        List<String> sortedNames = new ArrayList<>(names);
        List<String> sortedNumbers = new ArrayList<>(numbers);

        for (int i = 0; i < sortedNames.size() - 1; i++) {
            for (int j = 0; j < sortedNames.size() - i - 1; j++) {
                if (sortedNames.get(j).compareToIgnoreCase(sortedNames.get(j + 1)) > 0) {
                    Collections.swap(sortedNames, j, j + 1);
                    Collections.swap(sortedNumbers, j, j + 1);
                }
            }
        }

        System.out.println("\n----- CONTACT LIST (SORTED) -----");
        for (int i = 0; i < sortedNames.size(); i++) {
            System.out.println((i + 1) + ". " + sortedNames.get(i) + " - " + sortedNumbers.get(i));
        }
    }

    static void saveContacts() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < names.size(); i++) {
                out.println(names.get(i) + "," + numbers.get(i));
            }
        } catch (IOException e) {
            System.out.println("Error saving contacts.");
        }
    }

    static void loadContacts() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                names.add(parts[0]);
                numbers.add(parts[1]);
            }
        } catch (IOException e) {
            System.out.println("Error loading contacts.");
        }
    }
}
