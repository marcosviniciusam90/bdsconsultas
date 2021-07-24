package com.devsuperior.uri2621.repositories;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "SELECT PRODUCTS.NAME " +
            "FROM PRODUCTS " +
            "INNER JOIN PROVIDERS ON PRODUCTS.ID_PROVIDERS = PROVIDERS.ID " +
            "WHERE PROVIDERS.NAME LIKE CONCAT(:beginName, '%') " +
            "AND AMOUNT BETWEEN :min AND :max")
    List<ProductMinProjection> searchNative(String beginName, Integer min, Integer max);

    @Query("SELECT new com.devsuperior.uri2621.dto.ProductMinDTO(obj.name) " +
            "FROM Product obj " +
            "WHERE obj.provider.name LIKE CONCAT(:beginName, '%') " +
            "AND obj.amount BETWEEN :min AND :max")
    List<ProductMinDTO> searchJPQL(String beginName, Integer min, Integer max);

}
