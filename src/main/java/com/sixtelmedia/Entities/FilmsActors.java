package com.sixtelmedia.Entities;

import jdk.nashorn.internal.ir.IfNode;

import javax.persistence.*;

/**
 * Created by branden on 3/14/16 at 17:26.
 */
@Entity
@Table(name = "film_actors")
public class FilmsActors {
    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne
    Film film;
    @ManyToOne
    Actor actor;


    public FilmsActors() {
    }

    public FilmsActors(Film film, Actor actor) {
        this.film = film;
        this.actor = actor;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}