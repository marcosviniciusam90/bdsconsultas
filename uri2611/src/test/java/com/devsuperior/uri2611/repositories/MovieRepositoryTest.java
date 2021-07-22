package com.devsuperior.uri2611.repositories;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository repository;

    @Test
    void searchNative() {
        List<MovieMinProjection> list = repository.searchNative("Action");
        List<MovieMinDTO> result = list.stream().map(MovieMinDTO::new)
                .collect(Collectors.toList());

        for (MovieMinDTO obj : result) {
            System.out.println(obj);
        }

        Assertions.assertEquals(2, result.size());
    }

    @Test
    void searchJPQL() {
        List<MovieMinDTO> result = repository.searchJPQL("Action");

        for (MovieMinDTO obj : result) {
            System.out.println(obj);
        }

        Assertions.assertEquals(2, result.size());
    }
}