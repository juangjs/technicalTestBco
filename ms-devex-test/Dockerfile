FROM openjdk:17-jdk-alpine
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
RUN echo "appuser:123" | chpasswd
USER appuser:appgroup
ENV JAVA_OPTS="-XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom"
COPY /applications/app-service/build/libs/devex-app.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]