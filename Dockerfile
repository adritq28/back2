# Usar una imagen base con OpenJDK
FROM openjdk:21-jdk

# Directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el JAR del proyecto al contenedor
COPY target/helvetas-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que la aplicación escucha
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
