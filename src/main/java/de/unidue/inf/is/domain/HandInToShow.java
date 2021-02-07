package de.unidue.inf.is.domain;

public class HandInToShow {
    private String name;
    private String text;
    private String grade;

    public HandInToShow(String name, String text, String grade) {
        this.name = name;
        this.text = text;
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
