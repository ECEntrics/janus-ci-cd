// This script is based on the source bellow:
// https://gist.github.com/ivan-pinatti/830ec918781060df03b12efd4a14096e
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.plugins.credentials.impl.*
import jenkins.model.Jenkins
import org.jenkinsci.plugins.plaincredentials.impl.*

def dockerhubUser = new File("/run/secrets/jenkins_dockerhub_username").text.trim()
def dockerhubPassword = new File("/run/secrets/jenkins_dockerhub_password").text.trim()

def jenkinsKeyUsernameWithPasswordParameters = [
        description: 'Concordia docker-hub',
        id         : 'docker-hub-concordia',
        password   : dockerhubPassword,
        userName   : dockerhubUser
]

// Gets Jenkins instance
Jenkins jenkins = Jenkins.getInstance()

// Gets credentials domain
def globalDomain = Domain.global()

// Gets credentials store
def credentialsStore = jenkins.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

// Defines username and password credentials
def jenkinsKeyUsernameWithPassword = new UsernamePasswordCredentialsImpl(
        CredentialsScope.GLOBAL,
        jenkinsKeyUsernameWithPasswordParameters.id,
        jenkinsKeyUsernameWithPasswordParameters.description,
        jenkinsKeyUsernameWithPasswordParameters.userName,
        jenkinsKeyUsernameWithPasswordParameters.secret
)

// Adds credential to store
credentialsStore.addCredentials(globalDomain, jenkinsKeyUsernameWithPassword)

// Saves to disk
jenkins.save()