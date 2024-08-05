package com.bicevida.prueba;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class BiceVidaTestApplicationTests {

    @Test
    public void testInsuranceClientsByRUT() {

        Map<String, List<String>> expected = Map.of(
                "SEGURO APV", List.of("7317855K", "73826497", "94020190", "99804238"),
                "SEGURO DE VIDA", List.of("7317855K", "73826497", "86620855", "94020190"),
                "SEGURO COMPLEMENTARIO DE SALUD", List.of("7317855K", "73826497", "86620855", "88587715", "94020190")
        );
        Map<String, List<String>> actual = BiceVidaTestApplication.insuranceClientsByRUT();

        assertEquals(expected, actual, "Los RUTs de los clientes no coinciden con los esperados.");
    }

    @Test
    public void testHigherClientsBalances() {
        List<Integer> expectedBalances = List.of(89000, 38200);
        List<Integer> actualBalances = BiceVidaTestApplication.higherClientsBalances();

        assertTrue(actualBalances.stream().allMatch(balance -> balance > 30000),
                "Todos los saldos deben ser mayores a 30.000.");

        for (int i = 1; i < actualBalances.size(); i++) {
            assertTrue(actualBalances.get(i - 1) >= actualBalances.get(i),
                    "Los saldos deben estar ordenados en orden descendente.");
        }

        assertEquals(expectedBalances, actualBalances,
                "Los saldos no están en el orden y valores esperados.");
    }

    @Test
    public void testInsuranceSortedByHighestBalance() {
        List<Integer> expectedInsuranceIds = List.of(2, 1, 3);

        List<Integer> actualInsuranceIds = BiceVidaTestApplication.insuranceSortedByHighestBalance();

        assertEquals(expectedInsuranceIds, actualInsuranceIds,
                "Los IDs de los seguros no están ordenados en el orden esperado basado en el saldo total administrado.");
    }

}
