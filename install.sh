#!/bin/bash

# This script will:
# 	- install docker
# 	- make temporary changes to your system (vm.max_map_count, fs.file-max and other)

# ATENTION: run this script as root (use sudo if needed)!

# Checks for root privileges
if [ "$EUID" -ne 0 ]
	then echo "Please run as root."
	exit 1
fi

# Checks if ports 80 and 443 are in use
SHOULD_EXIT=0
if [[ `lsof -i -P -n | grep LISTEN | grep '*:80 (LISTEN)'` ]]; then
	PORT80USER=`lsof -i -P -n | grep LISTEN | grep '*:80 (LISTEN)' | awk '{ print $1 }'`
	echo "Port 80 is in use by another program ($PORT80USER). Please free the port and try again."
	SHOULD_EXIT=1
fi

if [[ `lsof -i -P -n | grep LISTEN | grep '*:443 (LISTEN)'` ]]; then
	PORT443USER=`lsof -i -P -n | grep LISTEN | grep '*:443 (LISTEN)' | awk '{ print $1 }'`
	echo "Port 443 is in use by another program ($PORT80USER). Please free the port and try again."
	SHOULD_EXIT=1
fi

if [[ "$SHOULD_EXIT" = "1" ]]; then
	echo "Exiting..."
	exit 1
fi

# Installs docker using the get.docker.com method
HOST_DOCKER_VERSION="18.09"
VERSION=$HOST_DOCKER_VERSION
curl -fsSL https://get.docker.com -o get-docker.sh | sh
