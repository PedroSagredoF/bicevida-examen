# Usar la imagen base de Java 17
FROM openjdk:17-jdk-slim

# Copiar el archivo JAR desde el directorio target
COPY target/BiceVidaTest-0.0.1-SNAPSHOT.jar /app/BiceVidaTest.jar

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8081

# Definir el comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/BiceVidaTest.jar"]b"]