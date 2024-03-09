# Etapa de compilación
FROM gradle:8.6.0-jdk21 as BUILDER

# Define la carpeta de la aplicación en la imagen
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

# Copia los archivos de configuración de Gradle
COPY build.gradle settings.gradle /app/

# Copia la carpeta 'gradle' para aprovechar la caché de dependencias
COPY gradle $APP_HOME/gradle

# Copia el resto de los archivos (excepto los listados en .dockerignore)
COPY --chown=gradle:gradle . /home/gradle/src

# Cambia al usuario 'root' para ejecutar los comandos de construcción
USER root

# Asigna los permisos adecuados al directorio de la aplicación
RUN chown -R gradle /home/gradle/src

# Intenta realizar una compilación inicial para aprovechar la caché
RUN gradle build || return 0

# Copia todos los archivos nuevamente y realiza la compilación final
COPY . .
RUN gradle clean build

# Etapa de ejecución
FROM amazoncorretto:21-alpine

# Define la carpeta de la aplicación en la imagen
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME

# Agrega un usuario no privilegiado para ejecutar la aplicación
RUN addgroup -S spring && adduser -S spring -G spring

# Cambia al usuario no privilegiado
USER spring:spring

# Define la ruta del JAR en la carpeta de construcción
ARG JAR_FILE=$APP_HOME/build/libs/*.jar

# Copia el JAR construido desde la etapa de construcción
COPY --from=BUILDER ${JAR_FILE} ./app.jar

# Expone el puerto en el que se ejecuta la aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación cuando el contenedor se inicia
ENTRYPOINT ["java", "-jar", "./app.jar"]