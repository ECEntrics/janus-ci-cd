# Janus
> A Dockerized Jenkins CI-CD system for Apella

Janus is a Docker image that contains an instance of Jenkins with all the necessary configuration built into it for
handling the CI-CD of Apella.

## Prerequisites

Only needs a working installation of Docker. 

## Installation

The credentials of the default admin user created are passed to Jenkins using docker secrets. The files containing the
username and password to be used can be found at the paths `./jenkins/secrets/user` and `./jenkins/secrets/pass`
respectively. Edit the files to change the default username and password.

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
`https://jenkins.apella.tk/project/staging-apella-multibranch-pipeline`.

In the `SETUP` you can find more details about the setup process, the credentials, plugins and other configuration that
is done automatically and how to change these.

## Contact

Apostolof – apotwohd@gmail.com

Distributed under the MIT license. See ``LICENSE`` for more information.
