package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        try{
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e)
        {
            throw new StoreException(e);
        }
    }

    public void showCourse() throws StoreException {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select name from kurs");
            preparedStatement.executeQuery();
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public void complete()
    {
        complete = true;
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
