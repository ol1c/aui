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

ENV DATABASE_URL=jdbc:mysql://category-db:3306/category_db
ENV DATABASE_DRIVER=com.mysql.cj.jdbc.Driver
ENV DATABASE_USERNAME=category_user
ENV DATABASE_PASSWORD=category_pass

ENV JPA_DIALECT=org.hibernate.dialect.MySQLDialect
ENV SPRING_JPA_GENERATE_DDL=true
ENV DDL_AUTO=update

ENV SPRING_H2_CONSOLE_enabled=true

ENV MICROSERVICES_ITEM_URL=http://item-service:8080

EXPOSE 8080

COPY target/category-service-${VERSION}.jar /opt/category-service/category-service.jar
CMD ["java", "-jar", "/opt/category-service/category-service.jar"]
