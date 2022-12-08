#!/bin/sh
APP_NAME=cloud-magic-api
APP_VERSION=-1.0.0-SNAPSHOT
JAR_NAME=$APP_NAME$APP_VERSION.jar

rm -f tpid

nohup java -jar -Xms256m -Xmx256m $JAR_NAME --spring.profiles.active=prod >/dev/null 2>&1 &

echo $! > tpid

echo Start cloud-magic-api Success!