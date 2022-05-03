package listeners;

import model.User;

import java.sql.*;

public class UserService {

    private Connection connection = null;

    public UserService(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/discord", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkConnection() {
        try {
            return this.connection.isValid(100);
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean checkConnection(int timeout) {
        try {
            return this.connection.isValid(timeout);
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteUser(long userID) {

        if (getUser(userID) == null) {
            return false;
        }

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM `users` WHERE userid="+userID);
        }catch (SQLException exception){
            return false;
        }

        return true;
    }

    public User addUser(long userID, long balance) {
        User user = getUser(userID);
        if(user!=null){
            return updateUser(userID, balance);
        }

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO `users`(`userid`, `balance`) VALUES (" + userID + ", " + balance + ")");
        }catch (SQLException e){
            return null;
        }
        return getUser(userID);

    }

    public User updateUser(long userID, long balance) {
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE `users` SET `balance`='" + balance + "' WHERE users.userid=" + userID);
        }catch (SQLException exception){
            return null;

        }

        return getUser(userID);
    }


    public User getUser(long userID) {

        ResultSet resultSet;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT userid, balance FROM `users` WHERE userid=" + userID);
            resultSet.next();
            return new User(userID, resultSet.getLong("balance"));
        }catch (SQLException exception){
            return null;
        }
    }

}
