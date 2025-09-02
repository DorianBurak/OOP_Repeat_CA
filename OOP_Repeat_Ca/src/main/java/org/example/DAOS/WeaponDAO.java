package org.example.DAOS;

import org.example.DTOs.weaponDTO;
import org.example.Exceptions.DaoException;
import org.example.json.JsonManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    //Q2
    public weaponDTO getEntityById(int id) throws DaoException {
        if (!weaponIdCache.contains(id)) {
            System.out.println("ID not found in cache. Skipping database query.");
            return null;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        weaponDTO weapon = null;

        try {
            connection = this.getConnection();
            String query = "SELECT * FROM weapon WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int Weaponid = resultSet.getInt("id");
                String Name = resultSet.getString("name");
                String Type = resultSet.getString("type");
                double Weight = resultSet.getDouble("weight");
                int Durability = resultSet.getInt("durability");
                int Attack = resultSet.getInt("attack");
                String Motivity = resultSet.getString("motivity");
                String Technique = resultSet.getString("technique");

                weapon = new weaponDTO(Weaponid, Name, Type, Weight, Durability, Attack, Motivity, Technique);
            }
        } catch (SQLException e) {
            throw new DaoException("getEntityById() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) freeConnection(connection);
            } catch (SQLException e) {
                throw new DaoException("getEntityById() Closing resources failed: " + e.getMessage());
            }
        }
        return weapon;
    }

    //Q3
    public void deleteEntityById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getConnection();
            String query = "DELETE FROM weapon WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully deleted weapon with id " + id);
                weaponIdCache.remove(id);
            } else {
                System.out.println("No weapon found with id " + id);
            }
        } catch (SQLException e) {
            throw new DaoException("deleteWeaponById() " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("Error closing resources: " + e.getMessage());
            }
        }
    }

    //Q4
    public weaponDTO insertEntity(weaponDTO weapon) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;

        try {
            connection = this.getConnection();
            String query = "INSERT INTO weapon (name, type, weight, durability, attack, motivity, technique) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, weapon.getName());
            preparedStatement.setString(2, weapon.getType());
            preparedStatement.setDouble(3, weapon.getWeight());
            preparedStatement.setInt(4, weapon.getDurability());
            preparedStatement.setInt(5, weapon.getAttack());
            preparedStatement.setString(6, weapon.getMotivity());
            preparedStatement.setString(7, weapon.getTechnique());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insertion failed, no rows affected.");
            }

            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newId = generatedKeys.getInt(1);
                weapon.setId(newId);
                // Add to cache
                weaponIdCache.add(newId);
            } else {
                throw new SQLException("Insertion failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new DaoException("insertWeapon() " + e.getMessage());
        } finally {
            try {
                if (generatedKeys != null) generatedKeys.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) freeConnection(connection);
            } catch (SQLException e) {
                throw new DaoException("insertWeapon() " + e.getMessage());
            }
        }
        return weapon;
    }

    //Q5
    public List<weaponDTO> getEntitiesByFilter(String column, String value) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<weaponDTO> filteredList = new ArrayList<>();

        try {
            connection = this.getConnection();
            List<String> allowedColumns = List.of("name", "type", "weight", "durability", "attack", "motivity", "technique");
            if (!allowedColumns.contains(column.toLowerCase())) {
                throw new DaoException("Invalid filter column.");
            }

            String query = "SELECT * FROM weapon WHERE " + column + " LIKE ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + value + "%");

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

                weaponDTO weapon = new weaponDTO(id, Name, Type, Weight, Durability, Attack, Motivity, Technique);
                filteredList.add(weapon);
            }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving filtered entities: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) freeConnection(connection);
            } catch (SQLException e) {
                throw new DaoException("Error closing resources: " + e.getMessage());
            }
        }
        return filteredList;
    }

    //Q6
    private final Set<Integer> weaponIdCache = new HashSet<>();

    public WeaponDAO() {
        try {
            initializeCache();
        } catch (DaoException e) {
            System.err.println("Error initializing weapon ID cache: " + e.getMessage());
        }
    }

    private void initializeCache() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.getConnection();
            String query = "SELECT id FROM weapon";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                weaponIdCache.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            throw new DaoException("initializeCache() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) freeConnection(connection);
            } catch (SQLException e) {
                throw new DaoException("initializeCache() Closing resources failed: " + e.getMessage());
            }
        }
    }

}
