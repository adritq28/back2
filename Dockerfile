FROM openjdk:17-jdk

WORKDIR /app

COPY target/helvetas-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que la aplicación escucha
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
