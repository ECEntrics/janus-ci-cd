#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
docker exec -it jenkins ls /var/jenkins_home/plugins/ | grep -v jpi > "$DIR/../jenkins/plugins.txt"