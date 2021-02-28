package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.Delivery;
import de.unidue.inf.is.domain.HandIn;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EinreichenStore implements Closeable {
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
    public List<Integer> fetchAbgabeID(int kid) throws StoreException
    {
        makeConnection();
        try{
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select aid from dbp151.einreichen where kid=?");
            preparedStatement.setInt(1, kid);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> result = new ArrayList<>();
            while (resultSet.next())
            {
                result.add(resultSet.getInt("aid"));
            }
            resultSet.close();
            preparedStatement.close();
            complete = true;
            close();
            return result;
        }catch (SQLException | IOException e)
        {
            throw new StoreException(e);
        }
    }
    public int fetchResponder(int aid, int kid, int anummer) throws StoreException
    {
        makeConnection();
        try{
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from dbp151.einreichen where aid=? and kid=? and anummer=?");
            preparedStatement.setInt(1, aid);
            preparedStatement.setInt(2, kid);
            preparedStatement.setInt(3, anummer);
            ResultSet resultSet = preparedStatement.executeQuery();
            int result = 0;
            while (resultSet.next())
            {
                result = (resultSet.getInt("bnummer"));
            }
            resultSet.close();
            preparedStatement.close();
            complete = true;
            close();
            return result;
        }catch (SQLException | IOException e)
        {
            throw new StoreException(e);
        }
    }
    public int fetchAbgabeID(int bnummer, int kid, int anummer) throws StoreException
    {
        makeConnection();
        try{
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from dbp151.einreichen where bnummer=? and kid=? and anummer=?");
            preparedStatement.setInt(1, bnummer);
            preparedStatement.setInt(2, kid);
            preparedStatement.setInt(3, anummer);
            ResultSet resultSet = preparedStatement.executeQuery();
            //List<Integer> result = new ArrayList<>();
            int result = 0;
            while (resultSet.next())
            {
                //result.add(new HandIn(resultSet.getInt("bnummer"), resultSet.getInt("anummer"),
                        //resultSet.getInt("aid"), resultSet.getInt("kid")));
                result = (resultSet.getInt("aid"));
            }
            resultSet.close();
            preparedStatement.close();
            complete = true;
            close();
            return result;
        }catch (SQLException | IOException e)
        {
            throw new StoreException(e);
        }
    }
    public List<Delivery> fetchAllAnswers(int kid, int anummer) throws StoreException
    {
        makeConnection();
        try{
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from dbp151.einreichen where kid=? and anummer=?");
            preparedStatement.setInt(1, kid);
            preparedStatement.setInt(2, anummer);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<HandIn> firstResult = new ArrayList<>();
            while (resultSet.next())
            {
                firstResult.add(new HandIn(resultSet.getInt("bnummer"),
                        resultSet.getInt("anummer"), resultSet.getInt("aid"),
                        resultSet.getInt("kid")));
            }
            resultSet.close();
            preparedStatement.close();
            List<Delivery> result = new ArrayList<>();
            for(int i = 0; i < firstResult.size(); i++)
            {
                PreparedStatement preparedStatement1 = connection
                        .prepareStatement("select * from dbp151.abgabe where aid=?");
                preparedStatement1.setInt(1, firstResult.get(i).getaID());
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                while (resultSet1.next())
                {
                    result.add(new Delivery(resultSet1.getInt("aid"),
                            resultSet1.getString("abgabetext")));
                }
                resultSet1.close();
                preparedStatement1.close();
            }
            complete = true;
            close();
            return result;
        }catch (SQLException | IOException e)
        {
            throw new StoreException(e);
        }
    }
    public Delivery fetchAbgabeTextFromAbgabeID(int aid) throws StoreException
    {
        makeConnection();
        try{
                PreparedStatement preparedStatement = connection
                        .prepareStatement("select * from dbp151.abgabe where aid=?");
                preparedStatement.setInt(1, aid);
                ResultSet resultSet = preparedStatement.executeQuery();
                Delivery result = new Delivery();
                while (resultSet.next()) {
                    result.setaID(resultSet.getInt("aid"));
                    result.setAbgabeText(resultSet.getString("abgabetext"));
                }
                resultSet.close();
                preparedStatement.close();

            complete = true;
            close();
            return result;
        }catch (SQLException | IOException e)
        {
            throw new StoreException(e);
        }
    }
    public boolean insertText(String abgabeText, int kid, int anummer, int bnummer) throws StoreException
    {
        makeConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select aid from final table (insert into dbp151.abgabe (abgabetext) values(?))");
            preparedStatement.setString(1, abgabeText);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> result = new ArrayList<>();
            while (resultSet.next())
            {
                result.add(resultSet.getInt("aid"));
            }
            resultSet.close();
            preparedStatement.close();

            PreparedStatement preparedStatement1 = connection
                    .prepareStatement("insert into dbp151.einreichen (bnummer,kid,anummer,aid) values (?,?,?,?)");
            preparedStatement1.setInt(1, bnummer);
            preparedStatement1.setInt(2, kid);
            preparedStatement1.setInt(3, anummer);
            preparedStatement1.setInt(4, result.get(0));
            preparedStatement1.executeUpdate();

            preparedStatement1.close();
            complete = true;
            close();
            return complete;
        }catch (SQLException | IOException e)
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
