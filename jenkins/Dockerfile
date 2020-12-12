FROM jenkins/jenkins:lts
LABEL maintainers.1="apotwohd@gmail.com"
LABEL maintainers.2="ezerous@gmail.com"

USER root

# Preps Jenkins directories
RUN mkdir /var/log/jenkins
RUN mkdir /var/cache/jenkins
RUN chown -R jenkins:jenkins /var/log/jenkins
RUN chown -R jenkins:jenkins /var/cache/jenkins

# Installs docker inside image
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        apt-transport-https \
        ca-certificates \
        curl \
        gnupg-agent \
        software-properties-common && \
    curl -fsSL https://download.docker.com/linux/$(. /etc/os-release; echo "$ID")/gpg | apt-key add - && \
    add-apt-repository \
        "deb [arch=amd64] https://download.docker.com/linux/debian \
        $(lsb_release -cs) \
        stable" && \
   apt-get update && \
   apt-get install -y --no-install-recommends docker-ce && \
   apt-get clean && \
   usermod -aG docker jenkins

# Generates an SSH key-pair to use with Gitlab
RUN mkdir -p "/home/jenkins/.ssh"
RUN ssh-keygen -t ed25519 -C "ecentricsgr@gmail.com" -N "" -f "/home/jenkins/.ssh/gitlab-ssh"

USER jenkins

# Security configuration scripts
COPY configuration/* /var/jenkins_home/init.groovy.d/

# Automatically installs plugins
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# Adds job configurations for concordia
COPY jobs/. /var/jenkins_home/jobs/

# Sets defaults
# Gets rid of admin password setup
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false -Xmx8192m"
ENV JENKINS_OPTS="--handlerCountMax=300 --logfile=/var/log/jenkins/jenkins.log --webroot=/var/cache/jenkins/war"
