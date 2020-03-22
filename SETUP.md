# Installation

Welcome to **Janus** installation guide (the Jenkins based CI-CD systyem for Apella).

**This installation guide is intended for computers using Debian based, Linux distributions!**

The software provided will work on other operating systems too, after loosely following this guide, but changes and decisions may have to be made along the way.

Although the guide is pretty detailed don't hesitate to open an issue or contact us directly for any questions.

## Introduction

Janus is a docker image based on the official Jenkins image. Its purpose is to be used as a CI-CD system for Apella, but can be easily modified for use with any project, especially decentralized apps using the truffle suite.

## Prerequisites

To execute the commands bellow you should have a working docker installation on the machine you intend to use. If you don't have that we encourage you to use the installation script `install.sh` in the root directory.

The only other prerequisite is that ports 80 and 443 should be free.

## Building the image

To build the image use the makefile provided:
```bash
make build
```

## Installation

Set a variable to hold the host's docker version:
```bash
HOST_DOCKER_VERSION=$(docker version --format '{{.Server.Version}}')
```

You are now ready to start the Jenkins container:
```bash
make run
```

### Admin username and password

An admin user is automatically created. The username and password of this user are defined in the files `jenkins/pass` and `jenkins/user` respectively. **If you change them you need to build the image again for the changes to take effect.**

### Jenkins configuration

The initial configuration done includes setting up the security, setting up the system url and adding an SSH keypair credential to the system. The groovy scripts that make this happen can be found in the directory `jenkins/configuration`

The SSH keypair that is added is created on build time. You can get the public key running the utility script that was written for convenience:
```bash
./utility-scripts/printJenkinsGitlabSSHPublicKey.sh
```

**If you change any of the configuration scripts you need to build the image again for the changes to take effect.**

### Plugins installed

A number of plugins are automatically installed. A list of these plugins can be found in the file `jenkins/plugins.txt`. To update the plugins after having used the container and made changes, you may run the utility script that was written for convenience:
```bash
./utility-scripts/updateJenkinsPlugins.sh
```

**You will need to build the image again for the changes to take effect.**

### NodeJs versions installed

The plugins installed include the NodeJs plugin. A default configuration for the plugin is also provided in the file `jenkins/groovy/nodejs.groovy`. The script adds two configurations of the plugin, using node versions 12 and 13. Both configurations include the global installation of eslint and truffle npm packages.

**If you change the configuration script you need to build the image again for the changes to take effect.**

### Jobs added

A number of jobs (specific to Apella) are automaticaly configured. The config XML files are located in the directory `jenkins/jobs`.

## Uninstalling

To uninstall Janus just stop the container and remove the image:
```bash
make clean-data clean-images
```

You may also need to remove the files stored in the volumes:
```bash
sudo rm -rf volumes/
```

You may also want uninstall docker. Instructions about how to do this can be easily found online.
