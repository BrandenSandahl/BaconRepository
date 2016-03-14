package com.sixtelmedia.Entities;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(mappedBy = "actors")
    private List<Film> films;

//    @ManyToOne
//    private User user;




    public Actor() {
    }

    public Actor(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}