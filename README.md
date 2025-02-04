# Janus
> A Dockerized Jenkins CI-CD system for Concordia

Janus is a Docker image that contains an instance of Jenkins with all the necessary configuration built into it for
handling the CI-CD of Concordia.

## Prerequisites

Only needs a working installation of Docker. 

## Installation

The credentials of the default admin user created are passed to Jenkins using docker secrets. The files containing the
username and password to be used can be found at the paths `./jenkins/secrets/adminUsername` and
`./jenkins/secrets/adminPassword` respectively. Edit the files to change the default username and password.

The image also sets up a credential for use with docker when pushing images to docker hub. The username and password
used can be found at the paths `./jenkins/secrets/dockerhubUsername` and `./jenkins/secrets/dockerhubPassword`
respectively.

Then run:

```sh
make build
make run
```

During the Docker image build, a key-pair is created for usage with Gitlab. Get the public ssh key using:

```sh
make print-ssh-key
```

Then, on Gitlab, add the public key to the deploy keys of the project.

Jenkins also needs an access token from Gitlab to update build statuses. Create an access token and add it to Jenkins
under Global Credentials.

Lastly a Webhook must be added to Gitlab, pointing to the Jenkins project. On Gitlab, navigate to the Webhooks menu in
the Settings of the project and add a new webhook pointing to
`https://jenkins.ecentrics.net/project/concordia-multibranch-pipeline`.

To get the git branches and Jenkinsfiles from each branch initiate a `Multibranch Pipeline Scan`.

In the `SETUP` you can find more details about the setup process, the credentials, plugins and other configuration that
is done automatically and how to change these.

## Contact

Apostolof – apotwohd@gmail.com  
Ezerous - ezerous@gmail.com

Distributed under the MIT license. See ``LICENSE`` for more information.
