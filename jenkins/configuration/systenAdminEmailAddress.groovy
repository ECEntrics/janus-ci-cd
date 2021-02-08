import jenkins.model.JenkinsLocationConfiguration

def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()

jenkinsLocationConfiguration.setAdminAddress("ECEntrics <ecentricsgr@gmail.com>")

jenkinsLocationConfiguration.save()
