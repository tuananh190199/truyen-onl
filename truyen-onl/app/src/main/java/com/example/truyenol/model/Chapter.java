package com.example.truyenol.model;

public class Chapter {
    private int id;
    private String nameChapter;
    private String content;

    public Chapter() {
    }

    public Chapter(String nameChapter, String content) {
        this.nameChapter = nameChapter;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameChapter() {
        return nameChapter;
    }

    public void setNameChapter(String nameChapter) {
        this.nameChapter = nameChapter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", nameChapter='" + nameChapter + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
