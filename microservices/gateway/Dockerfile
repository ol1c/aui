FROM eclipse-temurin:21.0.8_9-jre

LABEL org.opencontainers.image.title="gateway"
LABEL org.opencontainers.image.source="https://github.com/ol1c/aui"
LABEL org.opencontainers.image.url="https://github.com/ol1c/aui"
LABEL org.opencontainers.image.version="0.0.1-SNAPSHOT"
LABEL org.opencontainers.image.description="Simple Vending Machine Service gateway infrastructure service."

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8080

ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:gateway
ENV SPRING_DATASOURCE_DRIVERCLASSNAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=password

ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV SPRING_JPA_GENERATE_DDL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

ENV SPRING_H2_CONSOLE_enabled=true

ENV MICROSERVICES_GATEWAY_HOST=localhost:8080
ENV MICROSERVICES_ITEM_URL=http://localhost:8081
ENV MICROSERVICES_CATEGORY_URL=http://localhost:8082

EXPOSE 8080

COPY target/gateway-${VERSION}.jar /opt/gateway/gateway.jar

CMD ["java", "-jar", "/opt/gateway/gateway.jar"]

