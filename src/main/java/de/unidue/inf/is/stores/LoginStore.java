package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginStore implements Closeable {
    private Connection connection;
    private boolean complete;

    public Connection makeConnection() throws StoreException
    {
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
            complete = false;
            return connection;
        } catch (SQLException e)
        {
            throw new StoreException(e);
        }
    }

    public boolean userAuthenticated(String email) throws StoreException
    {
        makeConnection();
        boolean answer = false;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from dbp151.benutzer where email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> result = new ArrayList<>();
            while (resultSet.next())
            {
                result.add(new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3)));
            }
            if (!result.isEmpty())
            {
                answer = true;
            }
            resultSet.close();
            preparedStatement.close();
            complete = true;
            close();
            return answer;
        } catch (SQLException | IOException e)
        {
            throw new StoreException(e);
        }

    }

    @Override
    public void close() throws IOException
    {
        if (connection != null)
        {
            try {
                if (complete)
                {
                    connection.commit();
                }
                else
                {
                    connection.rollback();
                }
            } catch (SQLException e)
            {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e)
                {
                    throw new StoreException(e);
                }
            }
        }
    }
}
