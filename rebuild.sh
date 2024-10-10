#!/bin/sh

docker compose -f docker-compose.yml down
./mvnw package -DskipTests -T=1000
docker compose -f docker-compose.yml up -d --build
