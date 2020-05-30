// This script is based on the sources listed bellow:
// https://gist.github.com/hayderimran7/d6ab8a6a770cb970349e
// https://gist.github.com/ivan-pinatti/de063b610d1bdf2da229c7874968f4d9
// https://support.cloudbees.com/hc/en-us/articles/217708168-create-credentials-from-groovy
import jenkins.model.Jenkins
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.plugins.credentials.impl.*
import hudson.util.Secret
import java.nio.file.Files
import net.sf.json.JSONObject
import org.jenkinsci.plugins.plaincredentials.impl.*

String privateKeyFilePath = "/home/jenkins/.ssh/gitlab-ssh"
File privateKeyFile = new File(privateKeyFilePath)
String privateKeyString = privateKeyFile.text

def gitlabSSHKeyParameters = [
  description:  'Gitlab SSH Key',
  id:           'gitlab-ssh-key',
  secret:       '',
  userName:     'git',
  key:          new BasicSSHUserPrivateKey.DirectEntryPrivateKeySource(privateKeyString)
]

// Gets Jenkins instance
Jenkins jenkins = Jenkins.getInstance()

// Gets credentials domain
def globalDomain = Domain.global()

// Gets credentials store
def credentialsStore = jenkins.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

// Defines private key
def privatKey = new BasicSSHUserPrivateKey(
  CredentialsScope.GLOBAL,
  gitlabSSHKeyParameters.id,
  gitlabSSHKeyParameters.username,
  gitlabSSHKeyParameters.key,
  gitlabSSHKeyParameters.secret,
  gitlabSSHKeyParameters.description
)

// Adds credential to store
credentialsStore.addCredentials(globalDomain, privatKey)

// Saves to disk
jenkins.save()