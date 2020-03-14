FROM jenkinsci/blueocean
LABEL maintainer="apotwohd@gmail.com"

# Preps Jenkins directories
USER root
RUN mkdir /var/log/jenkins
RUN mkdir /var/cache/jenkins
RUN chown -R jenkins:jenkins /var/log/jenkins
RUN chown -R jenkins:jenkins /var/cache/jenkins

# Installs docker inside container
# ATENTION: version MUST be the same as host's docker installation!
ARG VERSION=18.09
RUN curl -fsSL https://get.docker.com -o get-docker.sh | sh

# Generates an SSH key-pair to use with Gitlab
# TODO: replace placeholder email (note that this is not really necessary)
RUN mkdir -p "/home/jenkins/.ssh"
RUN ssh-keygen -t ed25519 -C "example@email.com" -N "" -f "/home/jenkins/.ssh/gitlab-ssh"

USER jenkins

# Security configuration scripts
COPY configuration/* /var/jenkins_home/init.groovy.d/

# Automatically installs plugins
# COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
# RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# Adds scripts for tool installation
# COPY groovy/* /usr/share/jenkins/ref/init.groovy.d/

# Sets defaults
# Gets rid of admin password setup
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false -Xmx8192m"
ENV JENKINS_OPTS="--handlerCountMax=300 --logfile=/var/log/jenkins/jenkins.log --webroot=/var/cache/jenkins/war"
