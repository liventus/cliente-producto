
FROM openjdk:17
# Copiar entrypoint.sh y darle permisos de ejecución

COPY target/cliente-producto-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "/app.jar"]