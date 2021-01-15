package com.vn.itplus.myrelish.dto;

import java.util.Date;

public class ItemReviewArticle {
    private String title;
    private String summary;
    private String content;
    private Date createdTime;
    private Date lastModifiedTime;

    public ItemReviewArticle(String title, String summary, String content, Date createdTime, Date lastModifiedTime) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.createdTime = createdTime;
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}
