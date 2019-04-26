package com.example.matthew.notesapp;

public class Note {
    private String title;
    private String idNum;
    private String text_content;

    public Note()
    {
        title = "<Empty>";
        idNum = "<Empty>";
        text_content = "<Empty>";

    }

    public Note(String title, String idNum, String text_content)
    {
        this.title = title;
        this.idNum = idNum;
        this.text_content = text_content;
    }

    public String getTitle()
    {
        return title;
    }

    public String getIdNum()
    {
        return idNum;
    }

    public String getText()
    {
        return text_content;
    }

    public String toString()
    {
        return title + ": " + idNum;
    }


}
