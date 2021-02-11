package de.unidue.inf.is.domain;

public class TaskToShow {
    private String courseName;
    private int aNummer;
    private String taskName;
    private String taskDescription;
    private int kID;

    public TaskToShow(String courseName, int aNummer, String taskName, String taskDescription, int kID) {
        this.courseName = courseName;
        this.aNummer = aNummer;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.kID = kID;
    }

    public int getkID() {
        return kID;
    }

    public void setkID(int kID) {
        this.kID = kID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getaNummer() {
        return aNummer;
    }

    public void setaNummer(int aNummer) {
        this.aNummer = aNummer;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
