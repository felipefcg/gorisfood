FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY target/*jar /app/gorisfood-api.jar

EXPOSE 8080

CMD [ "java", "-jar", "gorisfood-api.jar" ]