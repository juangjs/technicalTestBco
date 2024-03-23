FROM eclipse-temurin:17
COPY /applications/app-service/build/libs/devex-app.jar app.jar
COPY ./SQL/Scritp_DB_Pedidos_devex.sql /docker-entrypoint-initdb.d/
ENTRYPOINT [ "java", "-jar", "/app.jar" ]