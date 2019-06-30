package models;
import managers.ImagesManager;

import java.sql.Timestamp;

/**
 * Class representing image
 * This is a default class, used for saving user profile images
 */
public class Image implements Comparable <Image> {

    private int id, userId;
    private String url;
    private Timestamp createdAt;

    public Image(int id, int userId, String url, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.url = url;
        this.createdAt = createdAt;
    }

    /**
     * @return ID of an image
     */
    public int getId () { return id; }

    /**
     * @return user's ID
     */
    public int getUserId () { return userId; }

    /**
     * @return url of an image
     */
    public String getUrl () { return url; }

    /**
     * @return Date when created
     */
    public Timestamp getCreatedDate () { return createdAt; }

    @Override
    /**
     * @param o Passed image
     * @return Checks whether two images are same or not
     */
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Image)) return false;
        return url.equals(((Image) o).getUrl());
    }

    @Override
    /**
     * @param other passed image
     * @return compares two images
     */
    public int compareTo(Image other) { return Integer.compare(id, other.getId()); }

    @Override
    /**
     * String representation of an image
     */
    public String toString() {
        return id + " : " + url;
    }
}