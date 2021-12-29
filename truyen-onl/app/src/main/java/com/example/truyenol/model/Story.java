package com.example.truyenol.model;

import java.util.ArrayList;

public class Story {
    private int id;
    private String nameStory;
    private String type;
    private boolean status;
    private String description;
    private String author;
    private String linkImg;
    private int numberChapter;
    private ArrayList<Chapter> chapters;


    public Story() {
        nameStory=null;
        type=null;
        description=null;
        author=null;
        linkImg=null;
        chapters=null;
    }

    public Story(int id, String nameStory, String type, boolean status, String description, String author, String linkImg, int numberChapter) {
        this.id = id;
        this.nameStory = nameStory;
        this.type = type;
        this.status = status;
        this.description = description;
        this.author = author;
        this.linkImg = linkImg;
        this.numberChapter = numberChapter;
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", nameStory='" + nameStory + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", linkImg='" + linkImg + '\'' +
                ", numberChapter='" + numberChapter + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameStory() {
        return nameStory;
    }

    public void setNameStory(String nameStory) {
        this.nameStory = nameStory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }

    public int getNumberChapter() {
        return numberChapter;
    }

    public ArrayList<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }

    public void setNumberChapter(int numberChapter) {
        this.numberChapter = numberChapter;
    }
}
