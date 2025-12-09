FROM eclipse-temurin:21.0.8_9-jre

LABEL org.opencontainers.image.title="category-service"
LABEL org.opencontainers.image.source="https://github.com/ol1c/aui"
LABEL org.opencontainers.image.url="https://github.com/ol1c/aui"
LABEL org.opencontainers.image.version="0.0.1-SNAPSHOT"
LABEL org.opencontainers.image.description="Simple Vending Machine Service category application made in microservices architecture."

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8080

ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:category-service
ENV SPRING_DATASOURCE_DRIVERCLASSNAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=password

ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV SPRING_JPA_GENERATE_DDL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

ENV SPRING_H2_CONSOLE_enabled=true

ENV MICROSERVICES_ITEM_URL=http://localhost:8081

EXPOSE 8080

COPY target/category-service-${VERSION}.jar /opt/category-service/category-service.jar
CMD ["java", "-jar", "/opt/category-service/category-service.jar"]
