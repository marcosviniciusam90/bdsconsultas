package com.devsuperior.uri2621.repositories;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    void searchNative() {
        List<ProductMinProjection> list = repository.searchNative("P", 10,20);
        List<ProductMinDTO> result = list.stream().map(ProductMinDTO::new)
                .collect(Collectors.toList());

        for (ProductMinDTO obj : result) {
            System.out.println(obj);
        }

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void searchJPQL() {
        List<ProductMinDTO> result = repository.searchJPQL("P", 10,20);

        for (ProductMinDTO obj : result) {
            System.out.println(obj);
        }

        Assertions.assertEquals(1, result.size());
    }

}