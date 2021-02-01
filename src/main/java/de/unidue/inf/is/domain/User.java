package de.unidue.inf.is.domain;

public final class User {

    private String firstname;
    private String lastname;
    private String email;
    private int userNumber;


    public User() {
    }


    public User(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;

    }


    public String getFirstname() {
        return firstname;
    }


    public String getLastname() {
        return lastname;
    }

}