package by.epam.bookstore.model.connection;

import by.epam.bookstore.exception.DaoException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectorDB {
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;

    // TODO: 23.07.2020 Better for Singleton
    static {
        try {
            properties.load(new FileReader("property\\database.properties"));
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
        } catch (IOException | ClassNotFoundException e) {
            // TODO: 23.07.2020      //log.fatal;
        }
        DATABASE_URL = (String) properties.get("db.url");
    }

    public static Connection getConnection() throws DaoException {
        try {
            return DriverManager.getConnection(DATABASE_URL, properties);
        } catch (SQLException e) {
            throw new DaoException("Problem with getting Connection.", e);
        }
    }

}