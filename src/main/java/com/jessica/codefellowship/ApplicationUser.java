package com.jessica.codefellowship;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected long id;
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String dateOfBirth;
    protected String bio;


    public ApplicationUser() { }

    public ApplicationUser (String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public String toString() {
        return this.firstName + " " + this.lastName + " was created successfully.";
    }
}
