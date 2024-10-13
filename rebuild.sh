#!/bin/sh

docker compose -f docker-compose.yml down
./mvnw package -DskipTests -T1000
docker compose -f docker-compose.yml up -d --build
