package by.epam.bookstore.model.connection;

import by.epam.bookstore.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelperDB {

    private Connection connection;

    public HelperDB() throws DaoException {
        connection = ConnectorDB.getConnection();
    }

    public PreparedStatement getPreparedStatement(String sql) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new DaoException("Problem with PreparedStatement", e);
        }
        return preparedStatement;
    }

    public void closeResultSet(ResultSet resultSet) throws DaoException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DaoException("ResultSet unable to close");
            }
        }
    }

    public void closeStatement(PreparedStatement preparedStatement) throws DaoException {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new DaoException("PreparedStatement unable to close");
            }
        }
    }

    public void closeConnection() throws DaoException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException("Connection unable to close");
            }
        }
    }

}
