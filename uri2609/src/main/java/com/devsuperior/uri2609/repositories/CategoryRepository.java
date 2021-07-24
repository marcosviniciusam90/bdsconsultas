package com.devsuperior.uri2609.repositories;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.entities.Category;
import com.devsuperior.uri2609.projections.CategorySumProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(nativeQuery = true, value = "SELECT categories.name, SUM(products.amount) " +
            "FROM categories " +
            "INNER JOIN products ON products.id_categories = categories.id " +
            "GROUP BY categories.name")
    List<CategorySumProjection> searchNative();

    @Query("SELECT new com.devsuperior.uri2609.dto.CategorySumDTO(prod.category.name, SUM(prod.amount)) " +
            "FROM Product prod " +
            "GROUP BY prod.category.name")
    List<CategorySumDTO> searchJPQL();

}
