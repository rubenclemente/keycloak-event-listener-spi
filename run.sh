#!/usr/bin/env bash

clear
source ./setenv.sh

echo -e ""
echo -e "***********************************************************************"
echo -e "**                  Starting SpringBoot Application                  **"
echo -e "***********************************************************************"

DEBUG_PORT=7089
APP=xxx.war

#nohup java -Dspring.config.location=./config/ -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=$DEBUG_PORT,suspend=n -jar $APP &

#./mvnw clean spring-boot:run
./mvnw clean spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=$DEBUG_PORT"


echo Application Started!!
