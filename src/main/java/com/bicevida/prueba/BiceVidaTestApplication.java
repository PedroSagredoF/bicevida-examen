package com.bicevida.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class BiceVidaTestApplication {

    public static void main(String[] args) {

        SpringApplication.run(BiceVidaTestApplication.class, args);

        System.out.println("\n- IDs de Clientes");
        System.out.println(listClientsIds());
        System.out.println("_____________________________");

        System.out.println("\n- IDs de Clientes ordenados por Rut");
        System.out.println(listClientsIdsSortedByRUT());
        System.out.println("_____________________________");

        System.out.println("\n- Nombres de clientes ordenados de mayor a menor por la suma TOTAL\n" +
                "de los saldos de cada cliente en los seguros que participa:");
        System.out.println(sortClientsTotalBalances());
        System.out.println("_____________________________");

        System.out.println("\n- Objeto donde las claves son los nombres de los seguros y los\n" +
                "valores un arreglo con los RUTs de sus clientes ordenados alfabéticamente por nombre.");
        System.out.println(insuranceClientsByRUT());
        System.out.println("_____________________________");

        System.out.println("\n- Arreglo ordenado decrecientemente con los saldos de clientes que\n" +
                "tengan más de 30.000 en el \"Seguro APV\".");
        System.out.println(higherClientsBalances());
        System.out.println("_____________________________");

        System.out.println("\n- Arreglo con IDs de los seguros ordenados crecientemente por la\n" +
                "cantidad TOTAL de dinero que administran");
        System.out.println(insuranceSortedByHighestBalance());
        System.out.println("_____________________________");

        System.out.println("\n- Objeto en que las claves son los nombres de los Seguros y los\n" +
                "valores el número de clientes que solo tengan cuentas en ese seguro");
        System.out.println(uniqueInsurance());
        System.out.println("_____________________________");

        System.out.println("\n- Objeto en que las claves son los nombres de los Seguros y los\n" +
                "valores el ID de su cliente con menos fondos");
        System.out.println(clientWithLessFunds());
        System.out.println("_____________________________");

        System.out.println("\n- Nuevo cliente con datos ficticios y una cuenta en el \"SEGURO\n" +
                "COMPLEMENTARIO DE SALUD\" se devuelve el lugar que ocupa segun el ranking\n"+
                "entregado por el metodo listClientsIdsSortedByRUT()");
        System.out.println(newClientRanking());
        System.out.println("_____________________________");

    }

    private static final List<Cliente> clients = new ArrayList<>(List.of(
            new Cliente(1, "86620855", "DANIEL BUSTOS"),
            new Cliente(2, "7317855K", "NICOLAS PEREZ"),
            new Cliente(3, "73826497", "ERNESTO GRANADO"),
            new Cliente(4, "88587715", "JORDAN MARTINEZ"),
            new Cliente(5, "94020190", "ALEJANDRO ZELADA"),
            new Cliente(6, "99804238", "DENIS ROJAS")
    ));

    private static final List<Cuenta> accounts = new ArrayList<>(List.of(
            new Cuenta(6, 1, 15000),
            new Cuenta(1, 3, 18000),
            new Cuenta(5, 3, 135000),
            new Cuenta(2, 2, 5600),
            new Cuenta(3, 1, 23000),
            new Cuenta(5, 2, 15000),
            new Cuenta(3, 3, 45900),
            new Cuenta(2, 3, 19000),
            new Cuenta(4, 3, 51000),

            new Cuenta(5, 1, 89000),
            new Cuenta(1, 2, 1600),
            new Cuenta(5, 3, 37500),
            new Cuenta(6, 1, 19200),
            new Cuenta(2, 3, 10000),
            new Cuenta(3, 2, 5400),
            new Cuenta(3, 1, 9000),
            new Cuenta(4, 3, 13500),
            new Cuenta(2, 1, 38200),
            new Cuenta(5, 2, 17000),
            new Cuenta(1, 3, 1000),
            new Cuenta(5, 2, 600),
            new Cuenta(6, 1, 16200),
            new Cuenta(2, 2, 10000)
    ));

    private static final List<Seguro> insurances = List.of(
            new Seguro(1, "SEGURO APV"),
            new Seguro(2, "SEGURO DE VIDA"),
            new Seguro(3, "SEGURO COMPLEMENTARIO DE SALUD")
    );


    // Método para listar los IDs de clientes
    public static List<Integer> listClientsIds() {

        return clients.stream()
                .map(Cliente::getId)
                .toList();

    }


    // Método para listar los IDs de clientes ordenados por RUT

    public static List<Integer> listClientsIdsSortedByRUT() {
        return clients.stream()
                .sorted((c1, c2) -> {
                    // Extraer los RUTs sin el último dígito y convertir a enteros
                    int rut1 = Integer.parseInt(c1.getRut().substring(0, c1.getRut().length() - 1));
                    int rut2 = Integer.parseInt(c2.getRut().substring(0, c2.getRut().length() - 1));
                    return Integer.compare(rut1, rut2);
                })
                .map(Cliente::getId) // Obtener los IDs de los clientes
                .collect(Collectors.toList()); // Convertir a lista
    }


    /* Método para listar los nombres de clientes ordenados de mayor a menor por la suma TOTAL
     de los saldos de cada cliente en los seguros que participa */
    public static List<String> sortClientsTotalBalances() {
        return clients.stream()
                .sorted((c1, c2) -> {
                    int balance1 = getTotalBalanceForClient(c1.getId());
                    int balance2 = getTotalBalanceForClient(c2.getId());
                    return Integer.compare(balance2, balance1);
                })
                .map(Cliente::getName)
                .toList();
    }

    // Método auxiliar para calcular el saldo total de un cliente
    private static int getTotalBalanceForClient(int clientId) {
        return accounts.stream()
                .filter(account -> account.getClientId() == clientId)
                .mapToInt(Cuenta::getBalance)
                .sum();
    }


    /* Método para generar un objeto en que las claves sean los nombres de los seguros y los
     valores un arreglo con los RUTs de sus clientes ordenados alfabéticamente por nombre */
    public static Map<String, List<String>> insuranceClientsByRUT() {
        return insurances.stream()
                .collect(Collectors.toMap(
                        Seguro::getName,
                        seguro -> clients.stream()
                                .filter(client -> accounts.stream()
                                        .anyMatch(account -> account.getClientId() == client.getId() && account.getInsuranceId() == seguro.getId()))
                                .map(Cliente::getRut)
                                .sorted()
                                .toList()
                ));
    }


    /* Método para generar un arreglo ordenado decrecientemente con los saldos de clientes que
    tengan más de 30.000 en el "Seguro APV" */
    public static List<Integer> higherClientsBalances() {
        return accounts.stream()
                .filter(account -> {
                    // Filtra por el seguro "Seguro APV"
                    return insurances.stream()
                            .anyMatch(seguro -> seguro.getName().equals("SEGURO APV") && seguro.getId() == account.getInsuranceId());
                })
                .map(Cuenta::getBalance)
                .filter(balance -> balance > 30000)
                .sorted((b1, b2) -> Integer.compare(b2, b1))
                .toList();
    }


    /* Método para generar un arreglo con IDs de los seguros ordenados crecientemente por la
    cantidad TOTAL de dinero que administran */
    public static List<Integer> insuranceSortedByHighestBalance() {
        Map<Integer, Integer> insuranceBalances = accounts.stream()
                .collect(Collectors.groupingBy(
                        Cuenta::getInsuranceId,
                        Collectors.summingInt(Cuenta::getBalance)
                ));

        return insuranceBalances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()) // Ordena por saldo total
                .map(Map.Entry::getKey) // Obtiene el ID del seguro
                .toList();
    }


    /* Método para generar un objeto en que las claves sean los nombres de los Seguros y los
    valores el número de clientes que solo tengan cuentas en ese seguro */
    public static Map<String, Long> uniqueInsurance() {
        Map<Integer, List<Integer>> clientInsuranceMap = accounts.stream()
                .collect(Collectors.groupingBy(
                        Cuenta::getClientId,
                        Collectors.mapping(Cuenta::getInsuranceId, Collectors.toList())
                ));

        Map<Integer, List<Integer>> insuranceClientsMap = insurances.stream()
                .collect(Collectors.toMap(
                        Seguro::getId,
                        seguro -> clientInsuranceMap.entrySet().stream()
                                .filter(entry -> entry.getValue().size() == 1 && entry.getValue().contains(seguro.getId()))
                                .map(Map.Entry::getKey)
                                .collect(Collectors.toList())
                ));

        return insuranceClientsMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> insurances.stream()
                                .filter(seguro -> seguro.getId() == entry.getKey())
                                .findFirst()
                                .map(Seguro::getName)
                                .orElse("Unknown"),
                        entry -> (long) entry.getValue().size()
                ));
    }


    /* Método para generar un objeto en que las claves sean los nombres de los Seguros y los
    valores el ID de su cliente con menos fondos */
    public static Map<String, Integer> clientWithLessFunds() {

        Map<Integer, List<Cuenta>> insuranceAccountsMap = accounts.stream()
                .collect(Collectors.groupingBy(Cuenta::getInsuranceId));

        return insuranceAccountsMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> insurances.stream()
                                .filter(seguro -> seguro.getId() == entry.getKey())
                                .findFirst()
                                .map(Seguro::getName)
                                .orElse("Unknown"),
                        entry -> entry.getValue().stream()
                                .collect(Collectors.groupingBy(
                                        Cuenta::getClientId,
                                        Collectors.summingInt(Cuenta::getBalance)
                                ))
                                .entrySet().stream()
                                .min(Map.Entry.comparingByValue())
                                .map(Map.Entry::getKey)
                                .orElse(-1) // Default value if no client is found
                ));
    }


    // Método para agregar un nuevo cliente con datos ficticios y una cuenta en el "SEGURO
    //COMPLEMENTARIO DE SALUD" con un saldo de 15000 para este nuevo cliente, luego devolver el
    public static int newClientRanking() {
        int newClientId = clients.stream().mapToInt(Cliente::getId).max().orElse(0) + 1;
        Cliente newClient = new Cliente(newClientId, "12345678K", "MARIO CASTAÑEDA");
        clients.add(newClient);

        int seguroId = insurances.stream()
                .filter(seguro -> "SEGURO COMPLEMENTARIO DE SALUD".equals(seguro.getName()))
                .findFirst()
                .map(Seguro::getId)
                .orElseThrow(() -> new RuntimeException("Seguro no encontrado"));

        Cuenta newAccount = new Cuenta(newClientId, seguroId, 15000);
        accounts.add(newAccount);

        List<Integer> sortedClientIdsByRUT = listClientsIdsSortedByRUT();
        return sortedClientIdsByRUT.indexOf(newClientId) + 1; // Añadir 1 para el ranking basado en 1
    }

}
