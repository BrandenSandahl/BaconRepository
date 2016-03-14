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
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String releaseYear;

    @Transient
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


    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}

