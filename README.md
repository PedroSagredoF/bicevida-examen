# BiceVidaTestApplication

Este proyecto es una aplicación de demostración desarrollada en Java utilizando Spring Boot, que simula la gestión de seguros y clientes. La aplicación incluye varias funcionalidades, como listar clientes, ordenar clientes por RUT, calcular saldos totales, y más.

## Requisitos

- Java 17 o superior
- Maven 3.8.1 o superior

## Estructura del Proyecto

El proyecto contiene las siguientes clases principales:

- **BiceVidaTestApplication**: Clase principal que contiene métodos para procesar y manipular datos de clientes, seguros y cuentas.
- **Cliente**: Clase que representa a un cliente con un ID, RUT, y nombre.
- **Cuenta**: Clase que representa una cuenta de un cliente, asociada a un seguro, con un balance.
- **Seguro**: Clase que representa un seguro con un ID y un nombre.

## Funcionalidades Implementadas

1. **listClientsIds**: Lista los IDs de los clientes.
2. **listClientsIdsSortedByRUT**: Lista los IDs de clientes ordenados por RUT (sin contar el último dígito).
3. **sortClientsTotalBalances**: Lista los nombres de clientes ordenados de mayor a menor por la suma total de los saldos en los seguros que participan.
4. **insuranceClientsByRUT**: Genera un objeto donde las claves son los nombres de los seguros y los valores son arreglos con los RUTs de sus clientes, ordenados alfabéticamente.
5. **higherClientsBalances**: Genera un arreglo ordenado decrecientemente con los saldos de clientes que tengan más de 30.000 en el "Seguro APV".
6. **insuranceSortedByHighestBalance**: Genera un arreglo con IDs de los seguros ordenados crecientemente por la cantidad total de dinero que administran.
7. **uniqueInsurance**: Genera un objeto en el que las claves son los nombres de los seguros y los valores son el número de clientes que solo tienen cuentas en ese seguro.
8. **clientWithLessFunds**: Genera un objeto en el que las claves son los nombres de los seguros y los valores son el ID de su cliente con menos fondos.
9. **newClientRanking**: Agrega un nuevo cliente ficticio y devuelve su ranking según el método `listClientsIdsSortedByRUT`.

## Ejecución del Proyecto

### 1. Clonar el repositorio
Clona este repositorio en tu máquina local:
~~~
git clone https://github.com/PedroSagredoF/bicevida-examen.git
cd bicevida-examen 
~~~


### 2. Compilar el proyecto
Compila el proyecto usando Maven:
~~~
mvn clean install
~~~

### 3. Ejecutar la aplicación
Para ejecutar la aplicación:
~~~
mvn spring-boot:run
~~~
Esto ejecutará la aplicación y mostrará los resultados en la consola.

## Ejecución de Pruebas Unitarias
Las pruebas unitarias están incluidas en la clase BiceVidaTestApplicationTests. Para ejecutarlas, puedes usar Maven:
~~~
mvn test
~~~
Esto ejecutará las pruebas y mostrará los resultados en la consola. Las pruebas verifican la funcionalidad de los métodos clave en la aplicación, asegurando que los datos se procesen y devuelvan correctamente.

### Resultados esperados de las pruebas unitarias:
1. **testInsuranceClientsByRUT**: Verifica que los RUTs de los clientes en los seguros coincidan con los esperados.
2. **testHigherClientsBalances**: Verifica que los saldos devueltos sean mayores a 30.000 y que estén ordenados correctamente en orden descendente.
3. **testInsuranceSortedByHighestBalance**: Verifica que los IDs de los seguros se devuelvan en el orden correcto basado en el total de dinero administrado.
