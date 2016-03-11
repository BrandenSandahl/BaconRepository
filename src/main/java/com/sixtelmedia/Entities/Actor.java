package com.sixtelmedia.Entities;

import javax.persistence.*;

/**
 * Created by branden on 3/10/16 at 13:09.
 */
@Entity
public class Actor {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String name;


//    @ManyToOne
//    private User user;




    public Actor() {
    }

    public Actor(String name) {
        this.name = name;
//        this.user = user;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }


}