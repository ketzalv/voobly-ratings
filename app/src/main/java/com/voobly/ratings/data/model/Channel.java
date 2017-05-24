package com.voobly.ratings.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvazquez on 17/05/2017.
 */

public class Channel implements Serializable {
    private String  title           = "";
    private String  link            = "";
    private String  description     = "";
    private String  language        = "";
    private String  pubDate         = "";
    private String  lastBuildDate   = "";
    private String  generator       = "";
    private List<Feed> item = new ArrayList<>();

    public Channel(){

    }

    public List<Feed> getItem() {
        return item;
    }

    public void setItem(List<Feed> item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getLink() {
        return link;
    }

    public String getGenerator() {
        return generator;
    }

    public String getLanguage() {
        return language;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }
}
