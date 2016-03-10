package com.sixtelmedia.Entities;

import javax.persistence.*;
import java.time.LocalDate;

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
    private int jump;
    @Column(nullable = false)
    private LocalDate releaseYear;

    @ManyToOne
    private Actor actor;

    public Film() {
    }

    public Film(String name, int jump, LocalDate releaseYear) {
        this.name = name;
        this.jump = jump;
        this.releaseYear = releaseYear;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJump() {
        return jump;
    }

    public void setJump(int jump) {
        this.jump = jump;
    }

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(LocalDate releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}