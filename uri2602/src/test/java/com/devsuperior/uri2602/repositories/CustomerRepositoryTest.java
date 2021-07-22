package com.devsuperior.uri2602.repositories;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void searchNative() {
        List<CustomerMinProjection> list = customerRepository.searchNative("rs");
        List<CustomerMinDTO> result = list.stream().map(CustomerMinDTO::new)
                .collect(Collectors.toList());

        for (CustomerMinDTO obj : result) {
            System.out.println(obj);
        }

        Assertions.assertEquals(3, result.size());
    }

    @Test
    void searchJPQL() {
        List<CustomerMinDTO> result = customerRepository.searchJPQL("rs");

        for (CustomerMinDTO obj : result) {
            System.out.println(obj);
        }

        Assertions.assertEquals(3, result.size());
    }
}