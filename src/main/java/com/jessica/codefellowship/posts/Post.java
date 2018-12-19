package com.jessica.codefellowship.posts;

import com.jessica.codefellowship.applicationUsers.ApplicationUser;
import javafx.application.Application;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long id;
    public String body;
    public Date createdAt;

    @ManyToOne
    public ApplicationUser appUser;

    public Post() { }

    public Post(String body, Date createdAt) {
        this.body= body;
        this.createdAt = createdAt;
    }

    public String toString() {
        return this.body + " at " + this.createdAt;
    }
}
