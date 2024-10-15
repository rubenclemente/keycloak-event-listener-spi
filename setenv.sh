#!/usr/bin/env bash

echo -e " [INFO] Setting environment variables"

export DEVEL_HOME=C:\\devel
export JAVA_HOME=$DEVEL_HOME/tools/java/jdk-17.0.10
#export M2_HOME=$DEVEL_HOME/tools/apache-maven-3.8.1
export M2_HOME=$DEVEL_HOME/tools/apache-maven-3.9.9
export MVN_OPTS="-Dmaven.test.skip=$SKIP_TESTS -s settings.xml"

export PATH=$M2_HOME/bin:$JAVA_HOME/bin:$PATH


if [[ $1 == --print ]]; then
    echo -e "${g}DEVEL_HOME${n} = $DEVEL_HOME"
    echo -e "${g}JAVA_HOME${n} = $JAVA_HOME"
    echo -e "${g}M2_HOME${n} = $M2_HOME"
    echo -e "${g}MVN_OPTS${n} = $MVN_OPTS"
    echo -e "${g}PATH${n} = $PATH"
    echo -e "${g}VERSIONS:${n}"
    echo -e "============================="
    mvn --version
    java -version
    echo -e "============================="
    sleep 1
fi
