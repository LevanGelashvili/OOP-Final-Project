package managers;

import database.DatabaseAccessObject;
import models.Image;
import models.ItemImage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImagesManager {
    private static final String GET_USER_IMAGE_QUERY = "SELECT id, image_category_id, url," +
            " item_id, created_at FROM item_images WHERE user_id = ?;";
    private static final String GET_IMAGE_QUERY = "SELECT image_category_id, url, user_id," +
            " item_id, created_at FROM item_images WHERE id = ?;";

    private static final String GET_LAST_ID_ITEM = "SELECT MAX(id) FROM item_images;";
    private static final String GET_LAST_ID_PROFILE = "SELECT MAX(id) FROM profile_images;";

    private static final String GET_USER_PROFILE_IMAGE = "SELECT * FROM profile_images WHERE user_id = ?";

    private static final String INSERT_ITEM_IMAGE_QUERY = "INSERT INTO item_images VALUES(?, ?, ?, ?, ?, ?);";
    private static final String INSERT_PROFILE_IMAGE_QUERY = "INSERT INTO profile_images VALUES(?, ?, ?, ?);";

    private static final String INSERT_IMAGE_CATEGORY_QUERY = "INSERT INTO image_categories VALUES(?, ?);";
    private static DatabaseAccessObject DBO = DatabaseAccessObject.getInstance();

    /**
     * get Image object according to its id
     *
     * @param image_id
     * @return image from the database with given id
     */

    /**
     * Adds image into database, based on whether it's a profile image or item image
     * @param img
     * @return false if insertion fails
     */
    public static boolean addImage(Image img){
        return (img instanceof ItemImage) ? addItemImage((ItemImage) img) : addProfileImage(img);
    }

    /**
     * Adds image to itemImages
     * @param img
     * @return false if insertion fails
     */
    private static boolean addItemImage(ItemImage img) {

        try {
            PreparedStatement st = DBO.getPreparedStatement(INSERT_ITEM_IMAGE_QUERY);

            int generatedID = generateID(GET_LAST_ID_ITEM);

            st.setInt(1,generatedID);
            st.setInt(2, img.getImageCategory());
            st.setString(3, img.getUrl());
            st.setInt(4, img.getUserId());
            st.setInt(5, img.getItemId());
            st.setTimestamp(6, img.getCreatedDate());
            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Adds image to profile images
     * @param img
     * @returnfalse if insertion fails
     */
    private static boolean addProfileImage(Image img) {
        try {
            PreparedStatement st = DBO.getPreparedStatement(INSERT_PROFILE_IMAGE_QUERY);

            int generatedID = generateID(GET_LAST_ID_PROFILE);

            st.setInt(1,generatedID);
            st.setString(2, img.getUrl());
            st.setInt(3, img.getUserId());
            st.setTimestamp(4, img.getCreatedDate());
            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @return GeneratedID, taken from database
     * @throws SQLException
     */
    private static int generateID(String query) throws SQLException {
        PreparedStatement st = DBO.getPreparedStatement(query);
        ResultSet set = st.executeQuery();
        return (set.next()) ? 1 + set.getInt(1) : 1;
    }

    /**
     * get profile image of given user
     *
     * @param user_id
     * @return profile image
     */
    public static Image getUserProfileImage(int user_id){
        Image img = null;
        try {
            PreparedStatement st = DBO.getPreparedStatement(GET_USER_PROFILE_IMAGE);
            st.setInt(1, user_id);
            ResultSet set = st.executeQuery();
            if(!set.next()){
                return null;
            }
            img = new Image(set.getBigDecimal("id").intValue(),
                    set.getBigDecimal("user_id").intValue(),
                    set.getString("url"),
                    new Timestamp(set.getDate("created_at").getTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * get All item images of given user
     *
     * @param user_id
     * @return list of all images
     */

    public static List<ItemImage> getUserImages(int user_id){
        List<ItemImage> images = new ArrayList<>();
        try {
            PreparedStatement st = DBO.getPreparedStatement(GET_USER_IMAGE_QUERY);
            st.setInt(1, user_id);
            ResultSet set = st.executeQuery();

            while (set.next()) {
                ItemImage img = new ItemImage(set.getBigDecimal("id").intValue(),
                        set.getBigDecimal("image_category_id").intValue(),
                        set.getString("url"), user_id,
                        set.getBigDecimal("item_id").intValue(),
                        new Timestamp(set.getDate("created_at").getTime()));
                images.add(img);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    /**
     * Adds a type of image category in database
     * @param categoryID
     * @param name
     * @return false if insertion fails
     */
    /*
    //TODO : არ გვჭირდება, წინასწარ გვექნება განსზაღვრული როგორი ფოტოები გვინდა
    public static boolean addImageCategory(int categoryID, String name){
        try {
            PreparedStatement st = DBO.getPreparedStatement(INSERT_IMAGE_QUERY);
            st.setInt(1, categoryID);
            st.setString(2, name);
            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    */
}
