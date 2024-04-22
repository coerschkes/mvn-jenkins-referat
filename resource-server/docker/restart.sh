#!/bin/bash

pkill -f java
nohup java -jar "$@" >/dev/null 2>&1 &