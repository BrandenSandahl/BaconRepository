package com.sixtelmedia.Services;

import com.sixtelmedia.Entities.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


/**
 * Created by branden on 3/10/16 at 13:30.
 */
public interface FilmRepository extends PagingAndSortingRepository<Film, Integer> {
    Film findByName(String name);

    @Query("SELECT f FROM Film f WHERE f.name LIKE %?1%")
    List<Film> findByNameLike(String findFilm);

    Page<Film> findByCreatedById(Pageable pageable, int id);

//    @Query("SELECT f FROM Film f WHERE f.actor LIKE %?1%")
//    List<Film> findByActorLike(String findFilm);

}
