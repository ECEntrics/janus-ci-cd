## Deployment

To deploy the Jenkins server use the commands:
```bash
make build
make run
```

To get the administrator password created use the command:
```bash
docker exec jenkins_master_1 cat /var/jenkins_home/secrets/initialAdminPassword
```
