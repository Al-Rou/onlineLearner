package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.Course;
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

public class CourseStore implements Closeable {
    private Connection connection;
    private boolean complete;

    public CourseStore() throws StoreException
    {
        try{
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
            complete = false;
        } catch (SQLException e)
        {
            throw new StoreException(e);
        }
    }

    public Connection makeConnection() throws StoreException
    {
        try{
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
            complete = false;
            return connection;
        } catch (SQLException e)
        {
            throw new StoreException(e);
        }
    }
    public List<Course> showCourse() throws StoreException {
        makeConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select name, ersteller, freieplaetze from dbp151.kurs");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Course> result = new ArrayList<>();
            while (resultSet.next())
            {
                result.add(new Course(resultSet.getString(1), resultSet.getInt(2),
                        resultSet.getInt(3)));
            }
            /*if (result.isEmpty())
            {
                result.add("There is no course for you!");
            }*/
            resultSet.close();
            preparedStatement.close();
            complete = true;
            close();
            return result;
        }
        catch (SQLException | IOException e) {
            throw new StoreException(e);
        }
    }

    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                if (complete) {
                    connection.commit();
                }
                else {
                    connection.rollback();
                }
            }
            catch (SQLException e) {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }
}
