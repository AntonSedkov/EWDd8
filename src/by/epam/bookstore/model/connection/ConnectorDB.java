package by.epam.bookstore.model.connection;

import by.epam.bookstore.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectorDB {

    public static Connection getConnection() throws DaoException {
        try {
            ResourceBundle resource = ResourceBundle.getBundle("property.database");
            String url = resource.getString("db.url");
            Properties properties = new Properties();
            properties.put("user", resource.getString("db.user"));
            properties.put("password", resource.getString("db.password"));
            properties.put("autoReconnect", resource.getString("db.autoReconnect"));
            properties.put("characterEncoding", resource.getString("db.characterEncoding"));
            properties.put("useUnicode", resource.getString("db.useUnicode"));
            properties.put("useSSL", resource.getString("db.useSSL"));
            properties.put("serverTimezone", resource.getString("db.serverTimezone"));
            return DriverManager.getConnection(url, properties);
        } catch (MissingResourceException e) {
            throw new DaoException("Properties is not found. ", e);
        } catch (SQLException e) {
            throw new DaoException("Problem with Connection.", e);
        }
    }

    // TODO: 22.07.2020 WHY and WHERE to use???
    public static void registerDriver() throws DaoException {
        try {
            ResourceBundle resource = ResourceBundle.getBundle("property.database");
            String nameDriver = resource.getString("db.driver");
            Class.forName(nameDriver);
        } catch (ClassNotFoundException e) {
            throw new DaoException("Problem with Driver registration.", e);
        }
    }

    // TODO: 22.07.2020 WHY and WHERE to use???
    public static void deregisterDriver() throws DaoException {
        DriverManager.getDrivers().asIterator().forEachRemaining(
                driver -> {
                    try {
                        DriverManager.deregisterDriver(driver);
                    } catch (SQLException e) {
                        System.out.println("Deregistration Driver exception");
                        ;
                    }
                }
        );
    }

}
