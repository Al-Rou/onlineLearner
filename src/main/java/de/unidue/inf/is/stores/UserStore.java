package de.unidue.inf.is.stores;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.utils.DBUtil;



public final class UserStore implements Closeable {

    private Connection connection;
    private boolean complete;


    public Connection makeConnection() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
            complete = false;
            return connection;
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public List<User> fetchBNummerFromEmail(String email) throws StoreException {
        makeConnection();
        try {
            PreparedStatement preparedStatement = connection
                            .prepareStatement("select * from dbp151.benutzer where email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> listRes = new ArrayList<>();
            while (resultSet.next())
            {
                listRes.add(new User(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3)));
            }
            //result = listRes.get(0).getbNummer();
            resultSet.close();
            preparedStatement.close();
            complete = true;
            close();
            return listRes;
        } catch (SQLException | IOException e) {
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
