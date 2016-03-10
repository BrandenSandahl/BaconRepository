package com.sixtelmedia.Services;

import com.sixtelmedia.Entities.Film;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;


/**
 * Created by branden on 3/10/16 at 13:30.
 */
public interface FilmRepository extends CrudRepository<Film, Integer> {
}
