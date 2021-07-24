package com.devsuperior.uri2737.repositories;

import com.devsuperior.uri2737.dto.LawyerMinDTO;
import com.devsuperior.uri2737.projections.LawyerMinProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class LawyerRepositoryTest {

    private static final Map<String, Integer> EXPECTED_RESULT = Map.of(
            "Chelsey D. Sanders", 20,
            "Marty M. Harrison", 5,
            "Average", 12
    );

    @Autowired
    private LawyerRepository lawyerRepository;

    @Test
    void searchNative() {
        List<LawyerMinProjection> list = lawyerRepository.searchNative();
        List<LawyerMinDTO> result = list.stream().map(LawyerMinDTO::new)
                .collect(Collectors.toList());

        for (LawyerMinDTO obj : result) {
            System.out.println(obj);
            Assertions.assertEquals(EXPECTED_RESULT.get(obj.getName()), obj.getCustomersNumber());
        }

        Assertions.assertEquals(3, result.size());
    }

}