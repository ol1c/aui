FROM nginx:1.23.3

LABEL org.opencontainers.image.title="aui-ng"
LABEL org.opencontainers.image.version="1.0.0"
LABEL org.opencontainers.image.source="https://github.com/ol1c/aui"
LABEL org.opencontainers.image.url="https://github.com/ol1c/aui"
LABEL org.opencontainers.image.description="Simple Vending Machine Service."


ENV API_URL=http://localhost:8080/api

EXPOSE 80

ADD dist/ui/browser /usr/share/nginx/html/
ADD docker/etc/nginx/templates/ /etc/nginx/templates/
