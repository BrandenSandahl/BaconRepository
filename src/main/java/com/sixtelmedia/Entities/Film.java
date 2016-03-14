package com.sixtelmedia.Entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by branden on 3/10/16 at 13:14.
 */
@Entity
public class Film {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String releaseYear;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Actor> actors;

    @ManyToOne
    private User createdBy;

    public Film() {
    }

    public Film(String name, String releaseYear, User createdBy) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.createdBy = createdBy;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}

