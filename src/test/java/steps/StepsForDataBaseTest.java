package steps;

import core_data_base.DatabaseConfig;
import core_data_base.Queries;
import io.cucumber.java.ru.И;
import org.aeonbits.owner.ConfigFactory;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Assertions;

import java.sql.*;

public class StepsForDataBaseTest {

    private static Connection connection;

    private static long productId;

    @И("Подключение к базе данных")
    public static void prepareConnection() throws SQLException {
        DatabaseConfig databaseConfig = ConfigFactory.create(DatabaseConfig.class);
        String databaseUrl = databaseConfig.databaseUrl();
        String databaseUser = databaseConfig.databaseUser();
        String databasePass = databaseConfig.databasePass();

        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(databaseUrl);
        dataSource.setUser(databaseUser);
        dataSource.setPassword(databasePass);

        connection = dataSource.getConnection();
    }
    @И("Добавление товара с атрибутами: {string} {string} {string}")
    public static long addProduct(String name, String type, String isExotic) throws SQLException { // метод добавления товара
        try (PreparedStatement insertStatement = connection.
                prepareStatement(Queries.insertNewProduct, Statement.RETURN_GENERATED_KEYS)) {

            insertStatement.setString(1, name);
            insertStatement.setString(2, type);
            insertStatement.setInt(3, Integer.parseInt(isExotic));

            int affectedRows = insertStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet resultSet = insertStatement.getGeneratedKeys()) {
                long id = 0;
                while (resultSet.next()) {
                    id = resultSet.getLong(1);
                }

                productId = id;

                return id;
            }
        }
    }
    @И("Проверить добавление товара в таблицу")
    public static void checkProductAvailability() throws SQLException {//метод проверки добавления товара
        long id = productId;
        try (PreparedStatement selectStatement = connection.prepareStatement(Queries.selectProductById)) {
            selectStatement.setLong(1, id);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                boolean result = resultSet.next();
                Assertions.assertTrue(result, "Товар с id " + id + " не найден в таблице");
            }
        }
    }
    @И("Удалить товар")
    public void deleteProduct() throws SQLException {//метод удаления товара
        long id = productId;
        try (PreparedStatement deleteStatement = connection.prepareStatement(Queries.deleteProduct)) {
            deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();
        }
    }
    @И("Закрыть ресурсы")
    public static void closeConnection() throws SQLException { //постусловие для всех тестов
        connection.close();
    }


}
