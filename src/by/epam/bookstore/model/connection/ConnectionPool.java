package by.epam.bookstore.model.connection;

import by.epam.bookstore.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {
    INSTANCE;

    private final Logger logger = Logger.getLogger(ConnectionPool.class);
    private BlockingDeque<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenConnections;
    private final static int DEFAULT_POOL_SIZE = 10;

    ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenConnections = new ArrayDeque<>();
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.offer(new ProxyConnection(ConnectorDB.getConnection()));
            } catch (DaoException e) {
                logger.error(e);
            }
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.error(e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection.getClass() == ProxyConnection.class) {
            ProxyConnection proxy = (ProxyConnection) connection;
            givenConnections.remove(proxy);
            freeConnections.offer(proxy);
        } else {
            logger.error("Attempt to insert wrong Connection to Pool");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().closeConnectionInPool();
            } catch (InterruptedException | SQLException e) {
                logger.error(e);
            }
        }
        deregisterDriver();
    }

    private void deregisterDriver() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error(e);
            }
        });
    }

}