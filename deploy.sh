#!/usr/bin/env bash

clear
source ./setenv.sh

echo -e ""
echo -e "***********************************************************************"
echo -e "**                 Deploying SPI Listener to keycloak                **"
echo -e "***********************************************************************"

export KEYCLOAK_DIR=keycloak:/opt/keycloak
export DEPLOYMENTS=$KEYCLOAK_DIR/providers

echo $DEPLOYMENTS
docker cp target/sample-event-listener.jar $DEPLOYMENTS

echo Application Deployed!!
