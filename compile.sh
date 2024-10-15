#!/usr/bin/env bash

source ./setenv.sh

./mvnw clean $MVN_OPTS
./mvnw package $MVN_OPTS