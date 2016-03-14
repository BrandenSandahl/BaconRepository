package com.sixtelmedia.Services;

import com.sixtelmedia.Entities.Actor;
import com.sixtelmedia.Entities.Film;
import com.sixtelmedia.Entities.FilmsActors;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by branden on 3/14/16 at 17:23.
 */
public interface FilmActorsRepository extends CrudRepository<FilmsActors, Integer>{
    List<Actor> findActorByFilm(Film film);

}
