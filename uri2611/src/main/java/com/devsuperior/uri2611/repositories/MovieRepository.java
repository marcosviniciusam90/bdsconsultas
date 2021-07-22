package com.devsuperior.uri2611.repositories;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(nativeQuery = true, value = "SELECT movies.id, movies.name " +
            "FROM MOVIES " +
            "INNER JOIN GENRES ON MOVIES.ID_GENRES = GENRES.ID " +
            "WHERE genres.description = :genreName")
    List<MovieMinProjection> searchNative(String genreName);

    @Query("SELECT new com.devsuperior.uri2611.dto.MovieMinDTO(obj.id, obj.name) " +
            "FROM Movie obj " +
            "WHERE obj.genre.description = :genreName")
    List<MovieMinDTO> searchJPQL(String genreName);
}
