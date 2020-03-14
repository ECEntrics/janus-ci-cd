#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
docker exec -it jenkins cat "/home/jenkins/.ssh/gitlab-ssh.pub"
