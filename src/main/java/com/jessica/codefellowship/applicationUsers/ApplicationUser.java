package com.jessica.codefellowship.applicationUsers;

import com.jessica.codefellowship.posts.Post;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long id;

    @Column(unique = true)
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String dateOfBirth;
    public String bio;

    @OneToMany(mappedBy="appUser")
    public List<Post> posts;


    @ManyToMany
    @JoinTable(
            name = "user_following",
            joinColumns = {@JoinColumn (name = "follower_id")},
            inverseJoinColumns = {@JoinColumn (name = "following_id")}
    )
    public Set<ApplicationUser> following; //User is following these people

    @ManyToMany(mappedBy = "following")
    public Set<ApplicationUser> followers; //Users following me



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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
