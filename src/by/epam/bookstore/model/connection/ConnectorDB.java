package by.epam.bookstore.model.connection;

import by.epam.bookstore.exception.DaoException;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectorDB {
    private static Logger logger = Logger.getLogger(ConnectorDB.class);
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;

    static {
        try {
            properties.load(new FileReader("src\\property\\database.properties"));
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
        } catch (IOException | ClassNotFoundException e) {
            logger.fatal("Can't find properties file or register driver", e);
        }
        DATABASE_URL = (String) properties.get("url");
    }

    static Connection getConnection() throws DaoException {
        try {
            return DriverManager.getConnection(DATABASE_URL, properties);
        } catch (SQLException e) {
            throw new DaoException("Problem with creating Connection.", e);
        }
    }

}