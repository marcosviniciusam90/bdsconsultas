package com.devsuperior.uri2990.repositories;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmpregadoRepositoryTest {

    private static final List<EmpregadoDeptDTO> EXPECTED_RESULT = List.of(
            new EmpregadoDeptDTO("1014332672", "Natalia Marques", "Pesquisa"),
            new EmpregadoDeptDTO("1733332162", "Tulio Vidal", "Ensino"),
            new EmpregadoDeptDTO("2434332222", "Aline Barros", "Pesquisa"),
            new EmpregadoDeptDTO("4244435272", "Juliana Rodrigues", "Ensino")
    );

    @Autowired
    private EmpregadoRepository empregadoRepository;

    @Test
    void searchNativeNotIn() {
        List<EmpregadoDeptProjection> list = empregadoRepository.searchNativeNotIn();
        List<EmpregadoDeptDTO> result = list.stream().map(EmpregadoDeptDTO::new)
                .collect(Collectors.toList());

        assertResult(result);

        assertEquals(4, result.size());
    }

    @Test
    void searchNativeLeftJoin() {
        List<EmpregadoDeptProjection> list = empregadoRepository.searchNativeLeftJoin();
        List<EmpregadoDeptDTO> result = list.stream().map(EmpregadoDeptDTO::new)
                .collect(Collectors.toList());

        assertResult(result);

        assertEquals(4, result.size());
    }

    @Test
    void searchJPQLNotIn() {
        List<EmpregadoDeptDTO> result = empregadoRepository.searchJPQLNotIn();

        assertResult(result);

        assertEquals(4, result.size());
    }

    private void assertResult(List<EmpregadoDeptDTO> result) {
        for (int i = 0; i < result.size(); i++) {
            EmpregadoDeptDTO empregado = result.get(i);
            System.out.println(empregado);
            assertEquals(EXPECTED_RESULT.get(i).getCpf(), empregado.getCpf());
            assertEquals(EXPECTED_RESULT.get(i).getEnome(), empregado.getEnome());
            assertEquals(EXPECTED_RESULT.get(i).getDnome(), empregado.getDnome());
        }
    }


}