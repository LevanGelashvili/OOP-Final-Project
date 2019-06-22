package managers;

import database.DatabaseAccessObject;
import models.Deal;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class UserManager {

    private static DatabaseAccessObject DBAO = DatabaseAccessObject.getInstance();

    private static final String SELECT_USER_QUERY = "SELECT * FROM users WHERE ? = ?;";
    private static final String STORE_USER_QUERY = "INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

    /**
     * get User object with its username
     *
     * @param username
     * @return User -null if user wasn't found
     */

    public static User getUserByUsername(String username){
        ArrayList<User> users = getUsersByColumn("username", username);
        if(users.isEmpty()) return null;
        return users.get(0);
    }

    /**
     * Given a column name in users table and a value
     * returns users array list corresponding to given value.
     * @param column
     * @param value
     * @return
     */
    public static ArrayList<User> getUsersByColumn(String column, String value){
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement st = DBAO.getPreparedStatement(SELECT_USER_QUERY);
            st.setString(1, column);
            st.setString(2, value);
            ResultSet set = st.executeQuery();
            while(set.next())
                users.add(getUserFromResultSetRow(set));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * get User object with its id
     *
     * @param id
     * @return User -null if user wasn't found
     */
    public static User getUserById(int id){
        ArrayList<User> users = getUsersByColumn("id", ""+id);
        if(users.isEmpty()) return null;
        return users.get(0);
    }


    /**
     * Should Store given user to database.
     * return true is user has been store successfully,
     * otherwise (if query violated any constraint returns false)
     * @param user
     * @return
     */
    public static boolean storeUser(User user){
        try {
            PreparedStatement st = DBAO.getPreparedStatement(STORE_USER_QUERY);
            st.setString(1, user.getPassword());
            st.setString(2, user.getUsername());
            st.setString(3, user.getFirstName());
            st.setString(4, user.getLastName());
            st.setString(5, user.getEmail());
            st.setString(6, user.getPhoneNumber());
            st.setTimestamp(7, user.getCreatedDate());
            st.setTimestamp(8, user.getUpdatedDate());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static User getUserFromResultSetRow(ResultSet set) throws SQLException {
        User user = new User(
                set.getInt("id"),
                set.getString("user_name"),
                set.getString("password"),
                set.getString("first_name"),
                set.getString("last_name"),
                set.getString("email"),
                set.getString("phone_number"),
                new Timestamp(set.getTimestamp("updated_at").getTime()),
                new Timestamp(set.getTimestamp("created_at").getTime()));

        return user;
    }
}