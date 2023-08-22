FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
ARG JAR_FILE
COPY target/${JAR_FILE} /app/gorisfood-api.jar

EXPOSE 8080

CMD [ "java", "-jar", "gorisfood-api.jar" ]