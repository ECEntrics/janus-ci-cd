build:
	@docker-compose -p ecentrics build;
run:
	@docker-compose -p ecentrics up -d
stop:
	@docker-compose -p ecentrics down
stop-clean-data:
	@docker-compose -p ecentrics down -v
copy-jobs:
	@docker cp janus:/var/jenkins_home/jobs/. ./jenkins/jobs
update-plugins:
	@docker exec -it janus ls -1 /var/jenkins_home/plugins/ | grep -v jpi > ./jenkins/plugins.txt
print-ssh-key:
	@docker exec -it janus cat "/home/jenkins/.ssh/gitlab-ssh.pub"
clean-images:
	@docker rmi `docker images -q -f "dangling=true"`
