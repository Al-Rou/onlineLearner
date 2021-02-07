package de.unidue.inf.is.domain;

public class HandInToShow {
    private String name;
    private String text;
    private String grade;
    private int aNummer;
    private int kID;

    public HandInToShow(String name, String text, String grade, int kID, int aNummer) {
        this.name = name;
        this.text = text;
        this.grade = grade;
        this.kID = kID;
        this.aNummer = aNummer;
    }

    public int getaNummer() {
        return aNummer;
    }

    public void setaNummer(int aNummer) {
        this.aNummer = aNummer;
    }

    public int getkID() {
        return kID;
    }

    public void setkID(int kID) {
        this.kID = kID;
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
