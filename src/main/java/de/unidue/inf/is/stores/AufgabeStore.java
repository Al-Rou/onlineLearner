package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.HandIn;
import de.unidue.inf.is.domain.Task;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AufgabeStore implements Closeable {
    private Connection connection;
    private boolean complete;
    public static UserStore userStore = new UserStore();

    public Connection makeConnection() throws StoreException
    {
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
            complete = false;
            return connection;
        }catch (SQLException e)
        {
           throw new StoreException(e);
        }
    }

    public List<HandIn> fetchTasksFromCourseID(int kid) throws StoreException
    {
        int anumm = userStore.fetchBNummerFromEmail(DBUtil.theUser);
        makeConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from dbp151.aufgabe where kid=? order by anummer asc");
            preparedStatement.setInt(1, kid);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Task> result1 = new ArrayList<>();
            while (resultSet.next())
            {
                result1.add(new Task(resultSet.getInt("kid"), resultSet.getInt("anummer"),
                        resultSet.getString("name"), resultSet.getString("beschreibung")));
            }
            resultSet.close();
            preparedStatement.close();
            List<HandIn> result2 = new ArrayList<>();
            for(int i=0; i < result1.size(); i++)
            {
                PreparedStatement preparedStatement1 = connection
                        .prepareStatement("select * from dbp151.einreichen where kid=? and bnummer=? and anummer=?");
                preparedStatement1.setInt(1, kid);
                preparedStatement1.setInt(2, anumm);
                preparedStatement1.setInt(3, result1.get(i).getaNummer());
                ResultSet resultSet1 = preparedStatement1.executeQuery();

                while (resultSet1.next())
                {
                    result2.add(new HandIn(resultSet1.getInt("bnummer"), resultSet1.getInt("aid"),
                            resultSet1.getInt("anummer"), resultSet1.getInt("kid")));
                }
                resultSet1.close();
                preparedStatement1.close();
            }
            complete = true;
            close();
            return result2;
        }catch (SQLException | IOException e)
        {
            throw new StoreException(e);
        }
    }
    public String fetchNameFromAufgabeNummer(int anummer) throws StoreException
    {
        makeConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select name from dbp151.aufgabe where anummer=?");
            preparedStatement.setInt(1, anummer);
            ResultSet resultSet = preparedStatement.executeQuery();
            String result = "";
            while (resultSet.next())
            {
                result += (resultSet.getString(1));
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
    public String fetchTextFromAbgabeNummer(int aid) throws StoreException
    {
        makeConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select abgabetext from dbp151.abgabe where aid=?");
            preparedStatement.setInt(1, aid);
            ResultSet resultSet = preparedStatement.executeQuery();
            String result = "";
            while (resultSet.next())
            {
                result += (resultSet.getString(1));
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
    public int fetchGrade(int aid, int bnummer) throws StoreException
    {
        makeConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select avg(note) from dbp151.bewerten where aid=? and bnummer=?");
            preparedStatement.setInt(1, aid);
            preparedStatement.setInt(2, bnummer);
            ResultSet resultSet = preparedStatement.executeQuery();
            int result = 0;
            while (resultSet.next())
            {
                result = resultSet.getInt(1);
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
        if(connection != null)
        {
            try {
                if(complete)
                {
                    connection.commit();
                }
                else
                {
                    connection.rollback();
                }
            }catch (SQLException e)
            {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                }catch (SQLException e)
                {
                    throw new StoreException(e);
                }
            }
        }
    }
}
