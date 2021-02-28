package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.Course;
import de.unidue.inf.is.domain.Evaluation;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BewertenStore implements Closeable {
    private Connection connection;
    private boolean complete;

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
    public boolean checkIfExists(int bnummer, int aid) throws StoreException {
        makeConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from dbp151.bewerten where bnummer=? and aid=?");
            preparedStatement.setInt(1, bnummer);
            preparedStatement.setInt(2, aid);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Evaluation> list = new ArrayList<>();
            boolean result = true;
            while (resultSet.next())
            {
                list.add(new Evaluation(resultSet.getInt("bnummer"),
                        resultSet.getInt("aid"), resultSet.getInt("note"),
                        resultSet.getString("kommentar")));
            }
            if(list.isEmpty())
            {
                result = false;
            }
            preparedStatement.close();
            complete = true;
            close();
            return result;
        }
        catch (SQLException | IOException e) {
            throw new StoreException(e);
        }
    }
    public boolean insertGrade(int bnummer, int aid, int grade, String comment) throws StoreException {
        makeConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into dbp151.bewerten (bnummer,aid,note,kommentar) values (?,?,?,?)");
            preparedStatement.setInt(1, bnummer);
            preparedStatement.setInt(2, aid);
            preparedStatement.setInt(3, grade);
            preparedStatement.setString(4, comment);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            complete = true;
            close();
            return complete;
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
