package io.twodigits.urlshortener.model;

import javax.persistence.*;

@Entity
public class URL {
    /**
     * The unique ID of an URL
     */
    @Id
    private String id;

    /**
     * The URL for which a short URL is provided
     */
    @Lob
    private String url;

    /**
     * The ID of a user to which this URL belongs
     */
    private String user;

    public URL() {
    }

    public URL(String id, String url, String user) {
        this.id = id;
        this.url = url;
        this.user = user;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "URL{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
