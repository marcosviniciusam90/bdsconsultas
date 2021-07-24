package com.devsuperior.uri2609.repositories;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.projections.CategorySumProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class CategoryRepositoryTest {

    private static final Map<String, Long> EXPECTED_AMOUNT_SUM = Map.of(
            "vintage", 1000L,
            "wood", 850L,
            "luxury", 350L,
            "modern", 13000L
    );

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void searchNative() {
        List<CategorySumProjection> list = categoryRepository.searchNative();
        List<CategorySumDTO> result = list.stream().map(CategorySumDTO::new)
                .collect(Collectors.toList());

        for (CategorySumDTO obj : result) {
            System.out.println(obj);
            Assertions.assertEquals(EXPECTED_AMOUNT_SUM.get(obj.getName()), obj.getSum());
        }

        Assertions.assertEquals(4, result.size());
    }

    @Test
    void searchJPQL() {
        List<CategorySumDTO> result = categoryRepository.searchJPQL();

        for (CategorySumDTO obj : result) {
            System.out.println(obj);
            Assertions.assertEquals(EXPECTED_AMOUNT_SUM.get(obj.getName()), obj.getSum());
        }

        Assertions.assertEquals(4, result.size());
    }

}