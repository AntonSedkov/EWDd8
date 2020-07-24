package by.epam.bookstore.model.connection;

import by.epam.bookstore.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public enum PoolConnection {
    INSTANCE;

    private BlockingDeque<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenConnections;

    private final static int DEFAULT_POOL_SIZE = 24;

    PoolConnection(){
//register DricerManager
        //init pool param
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenConnections = new ArrayDeque<>();
//init connection
    }

    public Connection getConnection(){
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenConnections.offer(connection);
        } catch (InterruptedException e) {
            throw new DaoException("");
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws DaoException {
        if (connection.getClass()==ProxyConnection.class){
            ProxyConnection proxy = (ProxyConnection) connection;
            givenConnections.remove(proxy);
            freeConnections.offer(proxy);
        }else{
            throw new DaoException("Connection is not from Pool");
        }

    }

    public void destroyPool(){
        for (int i=0; i<DEFAULT_POOL_SIZE;i++){
            try {
                freeConnections.take().closeConnectionInPool();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        deregisterDriver();
    }

    private void deregisterDriver(){
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                // TODO: 24.07.2020 log.error e.printStackTrace();
            }
        });
    }

}
