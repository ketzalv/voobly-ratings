package com.voobly.ratings.data.model;

import java.io.Serializable;

/**
 * Created by jvazquez on 17/05/2017.
 */

public class Feed implements Serializable {

    private String forumTitle       = "";
    private String forumDescription = "";

    private String link             = "";
    private String title            = "";
    private String description      = "";
    private String pubDate          = "";
    private String guid             = "";
    private String author           = "";

    public Feed(){

    }

    public String getForumDescription() {
        return forumDescription;
    }

    public String getForumTitle() {
        return forumTitle;
    }

    public void setForumDescription(String forumDescription) {
        this.forumDescription = forumDescription;
    }

    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getGuid() {
        return guid;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
