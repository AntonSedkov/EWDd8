package by.epam.bookstore.model.connection;

import by.epam.bookstore.exception.DaoException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

class ConnectorDB {
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;

    static {
        try {
            properties.load(new FileReader("src\\property\\database.properties"));
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
        } catch (IOException | ClassNotFoundException e) {
            // TODO: 23.07.2020      //log.fatal;
        }
        DATABASE_URL = (String) properties.get("url");
    }

    /*

    static {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("property.database");
            String driverName = resourceBundle.getString("db.driver");
            properties.setProperty("url", resourceBundle.getString("db.url"));
            properties.setProperty("user", resourceBundle.getString("db.user"));
            properties.setProperty("password", resourceBundle.getString("db.password"));
            properties.setProperty("useSSL", resourceBundle.getString("db.useSSL"));
            properties.setProperty("serverTimezone", resourceBundle.getString("db.serverTimezone"));
            properties.setProperty("autoReconnect", resourceBundle.getString("db.autoReconnect"));
            properties.setProperty("characterEncoding", resourceBundle.getString("db.characterEncoding"));
            properties.setProperty("useUnicode", resourceBundle.getString("db.useUnicode"));
            Class.forName(driverName);
        } catch (ClassNotFoundException | MissingResourceException e) {
            // TODO: 25.07.2020 log 
        }
        DATABASE_URL = (String) properties.get("url");
    }*/

    static Connection getConnection() throws DaoException {
        try {
            return DriverManager.getConnection(DATABASE_URL, properties);
        } catch (SQLException e) {
            throw new DaoException("Problem with creating Connection.", e);
        }
    }

}