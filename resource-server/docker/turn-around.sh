#!/bin/bash
docker-compose down
docker build -t resource-server .
docker-compose up -d