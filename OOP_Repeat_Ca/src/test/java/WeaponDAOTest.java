package test.java;

import org.example.DAOS.WeaponDAO;
import org.example.DAOS.MySqlDao;
import org.example.DTOs.weaponDTO;
import org.example.Exceptions.DaoException;
import org.example.Json.JsonManager;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WeaponDAOTest {

    private WeaponDAO weaponDAO;
    private static Connection connection;

    @BeforeAll
    void setupAll() throws SQLException {
        connection = new MySqlDao().getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS weapon;");
            statement.execute("CREATE TABLE weapon (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "type VARCHAR(255) NOT NULL," +
                    "weight DOUBLE NOT NULL," +
                    "durability INT NOT NULL," +
                    "attack INT NOT NULL," +
                    "motivity VARCHAR(255) NOT NULL," +
                    "technique VARCHAR(255) NOT NULL);");
        } catch (SQLException e) {
            System.err.println("Error creating test table: " + e.getMessage());
            throw e;
        }

        // Initialize the DAO
        weaponDAO = new WeaponDAO();
    }

    @AfterAll
    void teardownAll() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @BeforeEach
    void setupEach() throws SQLException, DaoException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM weapon;");
        }
        weaponDAO.insertEntity(new weaponDTO(0, "Two Dragons Sword", "Greatsword", 8.2, 120, 180, "S", "B"));
        weaponDAO.insertEntity(new weaponDTO(0, "Puppet's Saber", "Saber", 5.5, 100, 150, "C", "A"));
        weaponDAO.insertEntity(new weaponDTO(0, "Master Chef's Knife", "Dagger", 2.1, 80, 100, "D", "S"));
    }

    @Test
    @DisplayName("Test get all entities from database")
    void testGetAllEntities() throws DaoException {
        List<weaponDTO> weapons = weaponDAO.getAllEntites();
        assertNotNull(weapons);
        assertEquals(3, weapons.size());
    }

    @Test
    @DisplayName("Test get entity by existing ID")
    void testGetEntityById_Existing() throws DaoException {
        weaponDTO weapon = weaponDAO.getEntityById(1);
        assertNotNull(weapon);
        assertEquals("Two Dragons Sword", weapon.getName());
    }

    @Test
    @DisplayName("Test get entity by non-existing ID")
    void testGetEntityById_NonExisting() throws DaoException {
        weaponDTO weapon = weaponDAO.getEntityById(999);
        assertNull(weapon);
    }

    @Test
    @DisplayName("Test deleting an existing entity by ID")
    void testDeleteEntityById_Existing() throws DaoException {
        assertNotNull(weaponDAO.getEntityById(1));
        weaponDAO.deleteEntityById(1);
        assertNull(weaponDAO.getEntityById(1));
        assertEquals(2, weaponDAO.getAllEntites().size());
    }

    @Test
    @DisplayName("Test deleting a non-existing entity by ID")
    void testDeleteEntityById_NonExisting() throws DaoException {
        weaponDAO.deleteEntityById(999);
        assertEquals(3, weaponDAO.getAllEntites().size());
    }

    @Test
    @DisplayName("Test inserting a new entity")
    void testInsertEntity() throws DaoException {
        weaponDTO newWeapon = new weaponDTO(0, "Aegis", "Shield", 10.0, 200, 50, "E", "C");
        weaponDTO insertedWeapon = weaponDAO.insertEntity(newWeapon);

        assertNotNull(insertedWeapon);
        assertTrue(insertedWeapon.getId() > 0);
        assertEquals(4, weaponDAO.getAllEntites().size());
        assertNotNull(weaponDAO.getEntityById(insertedWeapon.getId()));
    }

    @Test
    @DisplayName("Test filtering entities by a valid column")
    void testGetEntitiesByFilter_ValidColumn() throws DaoException {
        List<weaponDTO> filteredList = weaponDAO.getEntitiesByFilter("type", "Greatsword");
        assertEquals(1, filteredList.size());
        assertEquals("Two Dragons Sword", filteredList.get(0).getName());
    }

    @Test
    @DisplayName("Test filtering entities by a non-existent value")
    void testGetEntitiesByFilter_NoMatch() throws DaoException {
        List<weaponDTO> filteredList = weaponDAO.getEntitiesByFilter("name", "NonExistentWeapon");
        assertTrue(filteredList.isEmpty());
    }

    @Test
    @DisplayName("Test filtering entities by an invalid column")
    void testGetEntitiesByFilter_InvalidColumn() {
        DaoException thrown = assertThrows(DaoException.class, () -> {
            weaponDAO.getEntitiesByFilter("invalid_column", "some_value");
        });
        assertTrue(thrown.getMessage().contains("Invalid filter column."));
    }

    @Test
    @DisplayName("Test converting a single weaponDTO to JSONObject")
    void testWeaponToJson() {
        weaponDTO weapon = new weaponDTO(1, "Test Weapon", "Sword", 5.0, 100, 150, "C", "A");
        JSONObject jsonObject = new JSONObject(JsonManager.weaponToJson(weapon));
        assertNotNull(jsonObject);
        assertEquals(1, jsonObject.getInt("id"));
        assertEquals("Test Weapon", jsonObject.getString("name"));
    }

    @Test
    @DisplayName("Test converting a list of weaponDTOs to JSONArray")
    void testWeaponListToJson() {
        List<weaponDTO> weapons = new ArrayList<>();
        weapons.add(new weaponDTO(1, "Sword", "Sword", 5.0, 100, 150, "C", "A"));
        weapons.add(new weaponDTO(2, "Axe", "Axe", 8.0, 120, 180, "S", "B"));
        JSONArray jsonArray = new JSONArray(JsonManager.weaponListToJson(weapons));
        assertNotNull(jsonArray);
        assertEquals(2, jsonArray.length());
        assertEquals("Sword", jsonArray.getJSONObject(0).getString("name"));
        assertEquals("Axe", jsonArray.getJSONObject(1).getString("name"));
    }
}