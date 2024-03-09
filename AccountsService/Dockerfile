# Etapa de ejecución
FROM amazoncorretto:21-alpine
# Agrega un usuario no privilegiado
RUN addgroup -S spring && adduser -S spring -G spring

# Cambia al usuario no privilegiado
USER spring:spring

ARG JAR_FILE=build/libs/*.jar

# Copia el JAR construido desde la etapa de construcción
COPY ${JAR_FILE} app.jar

# Expone el puerto en el que se ejecuta tu aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación cuando el contenedor se inicia
ENTRYPOINT ["java", "-jar", "/app.jar"]