FROM amazoncorretto:21-alpine

WORKDIR /app


COPY src/main/resources/application.properties /app/config/application.properties


COPY target/SmartFinance-0.0.1-SNAPSHOT.jar /app/SmartFinance-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "SmartFinance-0.0.1-SNAPSHOT.jar", "--spring.config.location=/app/config/application.properties"]