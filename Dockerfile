# Usar una imagen base con Maven y JDK
FROM maven:3.8.4-openjdk-17 AS build

# Configurar el directorio de trabajo
WORKDIR /app

# Copiar el código fuente al contenedor
COPY . .

# Construir el proyecto usando Maven
RUN mvn clean package

# Usar una imagen base para ejecutar la aplicación
FROM openjdk:17-jdk-alpine

# Copiar el JAR construido desde el contenedor de construcción
COPY --from=build /app/target/helvetas-0.0.1-SNAPSHOT.jar /app.jar

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
