package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.Registration;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationStore implements Closeable {
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

    public List<Integer> fetchCourseIDFromUserID(int bnummer) throws StoreException
    {
        makeConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from dbp151.einschreiben where bnummer=?");
            preparedStatement.setInt(1, bnummer);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> result = new ArrayList<>();
            while (resultSet.next())
            {
                result.add(resultSet.getInt("KID"));
            }
            resultSet.close();
            preparedStatement.close();
            complete = true;
            close();
            return result;
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
