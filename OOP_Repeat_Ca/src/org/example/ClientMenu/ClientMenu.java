package org.example.ClientMenu;

import org.example.DAOS.WeaponDAO;
import org.example.DAOS.WeaponDAO;
import org.example.DTOs.weaponDTO;
import org.example.Exceptions.DaoException;
import org.example.Json.JsonManager;


import java.net.Socket;
import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.SequencedCollection;

public class ClientMenu {
    private WeaponDAO dao = new WeaponDAO();
    private Scanner scanner = new Scanner(System.in);
    final static  String SERVER_ADDRESS = "localhost";
    final static  int SERVER_PORT = 8888;

    public static void main(String[] args) {
        ClientMenu client = new ClientMenu();
        client.start();
    }

    public void start() {
        System.out.println("The client is running and ready to connect to the server.");

        displayMenu();

        System.out.println("Finished! - client is exiting.");
    }

    public void displayMenu() {
        int option;
        do {
            System.out.println("\n=== Weapons Database Menu ===");
            System.out.println("1. Find Weapons by ID");
            System.out.println("2. List All Weapons");
            System.out.println("3. Add a Weapon");
            System.out.println("4. Delete a Weapon");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    findWeaponById();
                    break;
                case 2:
                    listAllWeapons();
                    break;
                case 3:
                    addWeapon();
                    break;
                case 4:
                    deleteWeapon();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (option != 5);
    }

    //Q9
    private void findWeaponById() {
        System.out.print("Enter Weapon ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            weaponDTO weapon = dao.getEntityById(id);
            if (weapon != null) {
                System.out.println("Found: " + weapon);
                String json = JsonManager.weaponToJson(weapon);
                System.out.println("Weapon JSON: " + json);
            } else {
                System.out.println("No Weapon found with ID " + id);
            }
        } catch (DaoException e) {
            System.err.println("Error retrieving Weapon: " + e.getMessage());
        }
    }

    //Q10
    private void listAllWeapons() {
        try {
            List<weaponDTO> weapon = dao.getAllEntites();
            String jsonString = String.valueOf(JsonManager.weaponListToJson(weapon));
            System.out.println("JSON Output: \n" + jsonString);
        } catch (DaoException e) {
            System.err.println("Error converting Weapon to JSON: " + e.getMessage());
        }
    }

    //Q11
    private void addWeapon(){
        System.out.println("Enter Weapon Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter Weapon Type: ");
        String type = scanner.nextLine();

        System.out.println("Enter Weapon Weight: ");
        double weight = scanner.nextDouble();

        System.out.println("Enter Weapon Durability: ");
        int durability = scanner.nextInt();

        System.out.println("Enter Weapon Attack");
        int attack = scanner.nextInt();

        System.out.println("Enter Weapon Motivity");
        String motivity = scanner.nextLine();
        scanner.nextLine();

        System.out.println("Enter Weapon Technique");
        String technique = scanner.nextLine();


        weaponDTO newWeapon = new weaponDTO(0, name, type, weight, durability, attack, motivity, technique);

        try {
            weaponDTO insertedWeapon = dao.insertEntity(newWeapon);
            System.out.println("Inserted Successfully! New Weapon ID: " + insertedWeapon.getId());
            weaponDTO weapon = dao.getEntityById(insertedWeapon.getId());
            System.out.println("Found: " + weapon);
            String json = JsonManager.weaponToJson(weapon);
            System.out.println("Weapon JSON: " + json);
        } catch (DaoException e) {
            System.err.println("Error converting Weapon to JSON: " + e.getMessage());
        }
    }

    //Q12
    private void deleteWeapon() {
        System.out.print("Enter Weapon ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Are you sure you want to delete Weapon with ID " + id + "? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            try {
                dao.deleteEntityById(id);
                System.out.println("Weapon with ID " + id + " has been deleted.");
                weaponDTO weapon = dao.getEntityById(id);
                System.out.println("Found: " + weapon);
                String json = JsonManager.weaponToJson(weapon);
                System.out.println("Weapon JSON: " + json);
            } catch (DaoException e) {
                System.err.println("Error deleting Weapon: " + e.getMessage());
            }
        } else {
            System.out.println("Deletion canceled.");
        }
    }

}
