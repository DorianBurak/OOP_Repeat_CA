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
                System.out.println("No Warframes found.");
            } else {
                for (weaponDTO w : stats) {
                    System.out.println(w);
                }
            }
        } catch (DaoException e) {
            System.err.println("Error retrieving Warframes: " + e.getMessage());
        }
    }

    //Q2
    private void findWeaponById() {
        System.out.print("Enter Warframe ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        try {
            weaponDTO warframe = dao.getEntityById(id);
            if (warframe != null) {
                System.out.println("Found: " + warframe);
            } else {
                System.out.println("No Warframe found with ID " + id);
            }
        } catch (DaoException e) {
            System.err.println("Error retrieving Warframe: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.display();
    }
}

