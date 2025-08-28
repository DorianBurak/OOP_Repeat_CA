package org.example.Main;

import org.example.DAOS.WeaponDAO;
import org.example.DAOS.WeaponDAOImpl;
import org.example.DTOs.weaponDTO;
import org.example.Exceptions.DaoException;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private WeaponDAO dao = new WeaponDAO();
    private Scanner scanner = new Scanner(System.in);

    public void display(){
        int option;
        do{
            System.out.println("Welcome to the Weapons DAO");
            System.out.println("1. List All Weapons");
            System.out.println("2. Find Weapon by ID");
            System.out.println("3. Delete Weapon by ID");
            System.out.println("4. Add Weapon");
            System.out.println("5. Filter Weapons");
            System.out.println("10. Exit");
            option = scanner.nextInt();
            scanner.nextLine();

            switch(option){
                case 1:
                    listAllWeapons();
                    break;
                case 2:
                    findWeaponById();
                    break;
                case 3:
                    deleteWeaponById();
                    break;
                case 4:
                    insertWeapon();
                    break;
                case 5:
                    filterWeapons();
                    break;
                case 10:
                    System.out.println("Exiting");
            }
        }
        while(option != 0);
    }

    //Q1
    private void listAllWeapons() {
        try {
            List<weaponDTO> stats = dao.getAllEntites();
            if (stats.isEmpty()) {
                System.out.println("No Weapons found.");
            } else {
                for (weaponDTO w : stats) {
                    System.out.println(w);
                }
            }
        } catch (DaoException e) {
            System.err.println("Error retrieving weapons: " + e.getMessage());
        }
    }

    //Q2
    private void findWeaponById() {
        System.out.print("Enter Weapon ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            weaponDTO weapon = dao.getEntityById(id);
            if (weapon != null) {
                System.out.println("Found: " + weapon);
            } else {
                System.out.println("No Weapon found with ID " + id);
            }
        } catch (DaoException e) {
            System.err.println("Error retrieving Weapon: " + e.getMessage());
        }
    }

    //Q3
    private void deleteWeaponById() {
        System.out.print("Enter Weapon ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Are you sure you want to delete Weapon with ID " + id + "? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            try {
                dao.deleteEntityById(id);
                System.out.println("Weapon with ID " + id + " has been deleted.");
            } catch (DaoException e) {
                System.err.println("Error deleting Weapon: " + e.getMessage());
            }
        } else {
            System.out.println("Deletion canceled.");
        }
    }

    //Q4
    private void insertWeapon() {
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

        System.out.println("Enter Weapon Technique");
        String technique = scanner.nextLine();


        weaponDTO newWeapon = new weaponDTO(0, name, type, weight, durability, attack, motivity, technique);

        try {
            weaponDTO insertedWeapon = dao.insertEntity(newWeapon);
            System.out.println("Inserted Successfully! New Weapon ID: " + insertedWeapon.getId());
        } catch (DaoException e) {
            System.err.println("Error inserting Weapon: " + e.getMessage());
        }
    }

    //Q5
    private void filterWeapons() {
        System.out.println("Filter by (name, type, weight, durability, attack, motivity, technique): ");
        String column = scanner.nextLine();
        System.out.println("Enter value to filter by: ");
        String value = scanner.nextLine();

        try {
            List<weaponDTO> filteredWeapons = dao.getEntitiesByFilter(column, value);
            if (filteredWeapons.isEmpty()) {
                System.out.println("No matching weapons found.");
            } else {
                for (weaponDTO weapon : filteredWeapons) {
                    System.out.println(weapon);
                }
            }
        } catch (DaoException e) {
            System.err.println("Error filtering weapons: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.display();
    }
}

