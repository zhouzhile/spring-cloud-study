#!/bin/sh
APP_NAME=cloud-magic-api
APP_VERSION=-1.0.0-SNAPSHOT
JAR_NAME=$APP_NAME$APP_VERSION.jar
tpid=`ps -ef|grep $JAR_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Stop cloud-magic-api  Process...'
    kill -15 $tpid
fi
sleep 1
tpid=`ps -ef|grep $JAR_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Kill cloud-magic-api Process!'
    kill -9 $tpid
else
    echo 'Stop cloud-magic-api Success!'
fi
