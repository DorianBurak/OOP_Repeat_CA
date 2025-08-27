package org.example.DAOS;

import org.example.DTOs.weaponDTO;
import org.example.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeaponDAO extends org.example.DAOS.MySqlDao implements org.example.DAOS.WeaponDAOImpl {

    //Q1
    public List<weaponDTO> getAllEntites() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<weaponDTO> EntityList = new ArrayList<>();

        try {
            connection = this.getConnection();

            String query = "SELECT * FROM weapon";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String Name = resultSet.getString("name");
                String Type = resultSet.getString("type");
                double Weight = resultSet.getDouble("weight");
                int Durability = resultSet.getInt("durability");
                int Attack = resultSet.getInt("attack");
                String Motivity = resultSet.getString("motivity");
                String Technique = resultSet.getString("technique");

                weaponDTO T1 = new weaponDTO(id, Name, Type, Weight, Durability, Attack, Motivity, Technique);
                EntityList.add(T1);
            }
        } catch (SQLException e) {
            throw new DaoException("ListAllExpenses() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("ListAllExpenses() " + e.getMessage());
            }
        }
        return  EntityList;
    }
}