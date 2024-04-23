#!/bin/bash
docker stop jenkins
docker build -t jenkins:jcasc .
docker run --name jenkins --rm -d -p 8081:8080 jenkins:jcasc