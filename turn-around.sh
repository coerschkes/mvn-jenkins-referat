#!/bin/bash
docker-compose down
docker build -t resource-server resource-server/docker
docker build -t jenkins jenkins/docker
docker-compose up -d