package com.example.rsspaper;

public class CategoryObject {
    private String category;
    private String rssLink;

    public CategoryObject(String category, String rssLink) {
        this.category = category;
        this.rssLink = rssLink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRssLink() {
        return rssLink;
    }

    public void setRssLink(String rssLink) {
        this.rssLink = rssLink;
    }
}
