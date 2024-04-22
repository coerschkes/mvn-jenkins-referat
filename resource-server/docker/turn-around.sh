#!/bin/bash
docker-compose down
docker build -t resource-server .
docker-compose up -d
cat cat ~/.ssh/id_rsa.pub | ssh root@localhost -p 2222 "mkdir -p ~/.ssh && cat >> ~/.ssh/authorized_keys"
#todo: add jenkins key here