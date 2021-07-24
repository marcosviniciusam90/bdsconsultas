package com.devsuperior.uri2990.repositories;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.entities.Empregado;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

    @Query(nativeQuery = true, value =
            "SELECT empregados.cpf, empregados.enome, departamentos.dnome " +
            "FROM empregados " +
            "INNER JOIN departamentos ON empregados.dnumero = departamentos.dnumero " +
            "WHERE empregados.cpf NOT IN (" +
            "   SELECT empregados.cpf " +
            "   FROM empregados " +
            "   INNER JOIN trabalha ON trabalha.cpf_emp = empregados.cpf " +
            ") " +
            "ORDER BY empregados.cpf")
    List<EmpregadoDeptProjection> searchNativeNotIn();

    @Query(nativeQuery = true, value =
            "SELECT empregados.cpf, empregados.enome, departamentos.dnome " +
                    "FROM empregados " +
                    "INNER JOIN departamentos ON empregados.dnumero = departamentos.dnumero " +
                    "LEFT JOIN trabalha ON trabalha.cpf_emp = empregados.cpf " +
                    "WHERE trabalha.cpf_emp IS NULL " +
                    "ORDER BY empregados.cpf")
    List<EmpregadoDeptProjection> searchNativeLeftJoin();

    @Query("SELECT new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(emp.cpf, emp.enome, emp.departamento.dnome) " +
            "FROM Empregado emp " +
            "WHERE emp.cpf NOT IN (" +
            "   SELECT emp.cpf " +
            "   FROM Empregado emp " +
            "   INNER JOIN emp.projetosOndeTrabalha " +
            ") " +
            "ORDER BY emp.cpf")
    List<EmpregadoDeptDTO> searchJPQLNotIn();

}
