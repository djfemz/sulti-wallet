package repositories;

import exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    private static final String DATABASE_URL="jdbc:mysql://localhost/sulti-wallet?createDatabaseIfNotExist=true";
    private static final String DATABASE_USERNAME = "";
    private static final String DATABASE_PASSWORD = "";

    private DatabaseConnectionManager(){}
    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            createAccountTable(connection);
            return connection;
        }catch (SQLException exception) {
            System.err.println("Error:: "+exception.getMessage());
            throw new DatabaseConnectionException(exception.getMessage());
        }
    }

    private static void createAccountTable(Connection connection) throws SQLException {
        String createAccountSql = """
                                      CREATE TABLE IF NOT EXISTS `accounts`(
                                      `id` INT NOT NULL AUTO_INCREMENT,
                                      `name` VARCHAR(255) NULL,
                                      `balance` DECIMAL(65) NULL,
                                      PRIMARY KEY (`id`),
                                      UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)""";

        PreparedStatement prepareStatement = connection.prepareStatement(createAccountSql);
        prepareStatement.execute();
    }
}
