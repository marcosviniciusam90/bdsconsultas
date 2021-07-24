package com.devsuperior.uri2737.repositories;

import com.devsuperior.uri2737.entities.Lawyer;
import com.devsuperior.uri2737.projections.LawyerMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {

    @Query(nativeQuery = true, value =
            "(SELECT NAME, CUSTOMERS_NUMBER AS customersNumber " +
            "FROM LAWYERS " +
            "WHERE CUSTOMERS_NUMBER = " +
            "(SELECT MAX(CUSTOMERS_NUMBER) FROM LAWYERS)) " +

            "UNION ALL " +

            "(SELECT NAME, CUSTOMERS_NUMBER " +
            "FROM LAWYERS " +
            "WHERE CUSTOMERS_NUMBER = " +
            "(SELECT MIN(CUSTOMERS_NUMBER) FROM LAWYERS)) " +

            "UNION ALL" +

            "(SELECT 'Average', ROUND(AVG(CUSTOMERS_NUMBER), 0) " +
            "FROM LAWYERS)")
    List<LawyerMinProjection> searchNative();


    // List<LawyerMinDTO> searchJPQL();
    // JPQL não tem UNION, então nesses casos usamos mesmo o SQL Nativo (como foi feito acima)
}
