# Installation

Welcome to **Janus** installation guide (the Jenkins based CI-CD systyem for Concordia).

**This installation guide is intended for computers using Debian based, Linux distributions!**

The software provided will work on other operating systems too, after loosely following this guide, but changes and
decisions may have to be made along the way.

Although the guide is pretty detailed don't hesitate to open an issue or contact us directly for any questions.

## Introduction

Janus is a docker image based on the official Jenkins image. Its purpose is to be used as a CI-CD system for Concordia,
but can be easily modified for use with any project, especially decentralized apps using the truffle suite.

## Prerequisites

To execute the commands below you should have a working docker installation on the machine you intend to use.

The only other prerequisite is that ports 80 and 443 should be free.

## Building the image

To build the image use the makefile provided:
```sh
make build
```

## Installation

You are now ready to start the Jenkins container:
```sh
make run
```

### Admin username and password

An admin user is automatically created. The username and password of this user are defined in the files
`jenkins/secrets/adminUsername` and `jenkins/secrets/adminPassword` respectively.
**If you change them you need to build the image again for the changes to take effect.**

### Jenkins configuration

The initial configuration done includes setting up the security, setting up the system url, adding an SSH keypair
credential to the system and finally adding a username and password credential for accessing docker hub. The groovy
scripts that make this happen can be found in the directory
`jenkins/configuration`.

The SSH keypair that is added is created on build time. You can get the public key running the utility script that was
written for convenience:
```sh
./utility-scripts/printJenkinsGitlabSSHPublicKey.sh
```

**If you change any of the configuration scripts you need to build the image again for the changes to take effect.**

### Plugins installed

A number of plugins are automatically installed. A list of these plugins can be found in the file `jenkins/plugins.txt`.
To update the plugins after having used the container and made changes, you may run the utility script that was written
for convenience:
```sh
./utility-scripts/updateJenkinsPlugins.sh
```

**You will need to build the image again for the changes to take effect.**

### Jobs added

A number of jobs (specific to Concordia) are automatically configured. The config XML files are located in the directory
`jenkins/jobs`.

## Uninstalling

To uninstall Janus just stop the container and remove the image:
```sh
make clean-data clean-images
```

You may also need to remove the files stored in the volumes:
```sh
sudo rm -rf volumes/
```

You may also want uninstall docker. Instructions about how to do this can be easily found online.
