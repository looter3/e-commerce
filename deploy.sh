#!/bin/bash

# Build projects with Maven
echo 'Build project'
mvn clean package


docker_projects=("e-commerce-product-service" "e-commerce-product-recommendation-service" "e-commerce-product-review-service" "e-commerce-product-composite-service")
# Compile docker images
for project in "${docker_projects[@]}"; do
    echo "Compiling docker image for: $project"
    cd $project
    mvn compile jib:dockerBuild
    cd ..
done

# Docker compose
docker-compose build
docker-compose up -d # Start
docker-compose logs -f # See logs
# docker-compose down # Shut down