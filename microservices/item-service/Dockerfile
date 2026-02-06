FROM eclipse-temurin:21.0.8_9-jre

LABEL org.opencontainers.image.title="item-service"
LABEL org.opencontainers.image.source="https://github.com/ol1c/aui"
LABEL org.opencontainers.image.url="https://github.com/ol1c/aui"
LABEL org.opencontainers.image.version="0.0.1-SNAPSHOT"
LABEL org.opencontainers.image.description="Simple Vending Machine Service item application made in microservices architecture."

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8080

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://item-db:5432/item_db
ENV DATABASE_DRIVER=org.postgresql.Driver
ENV DATABASE_USERNAME=item_user
ENV DATABASE_PASSWORD=item_pass

ENV JPA_DIALECT=org.hibernate.dialect.PostgreSQLDialect
ENV SPRING_JPA_GENERATE_DDL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=validate

ENV SPRING_H2_CONSOLE_enabled=true

ENV MICROSERVICES_CATEGORY_URL=http://category-service:8080

EXPOSE 8080

COPY target/item-service-${VERSION}.jar /opt/item-service/item-service.jar

CMD ["java", "-jar", "/opt/item-service/item-service.jar"]
