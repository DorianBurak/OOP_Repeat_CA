
package org.example.Main;

import org.example.DAOS.WeaponDAO;
import org.example.DAOS.WeaponDAOImpl;
import org.example.DTOs.weaponDTO;
import org.example.Exceptions.DaoException;

import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) throws DaoException {
        WeaponDAOImpl IUserDao = new WeaponDAO();

//  Q1
        try {
            System.out.println("\nCall getAllEntites()");
            List<weaponDTO> stats = IUserDao.getAllEntites();
            double totalExpenses = 0;
            if (stats.isEmpty())
                System.out.println("There are no Entries");
            else {

                for (weaponDTO weapon : stats) {
                    System.out.println(weapon.toString());
                    totalExpenses = totalExpenses + weapon.getId();
                }
            }
            System.out.println("All Complete entries: " + totalExpenses);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}