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
    private List<String> result = new ArrayList<>();

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    public CourseStore() throws StoreException
    {
        /*try{
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
            complete = false;
        } catch (SQLException e)
        {
            throw new StoreException(e);
        }*/
    }
    public void setCompleteFalse()
    {
        complete = false;
    }
    public void completeDone()
    {
        complete = true;
    }
    public Connection makeConnection() throws StoreException
    {
        try{
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
            setCompleteFalse();
            return connection;
        } catch (SQLException e)
        {
            throw new StoreException(e);
        }
    }
    public List<String> showCourse() throws StoreException {
        makeConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select name from dbp151.kurs");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> result = new ArrayList<>();
            while (resultSet.next())
            {
                result.add(resultSet.getString(1));
            }
            if (result.isEmpty())
            {
                result.add("There is no course for you!");
            }
            resultSet.close();
            preparedStatement.close();
            completeDone();
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
