build:
	@docker-compose build;
run:
	@docker-compose up -d
stop:
	@docker-compose down
stop-clean-data:
	@docker-compose down -v
copy-jobs:
	@docker cp jenkins:/var/jenkins_home/jobs/. ./jenkins/jobs
update-plugins:
	@docker exec -it jenkins ls -1 /var/jenkins_home/plugins/ | grep -v jpi > ./jenkins/plugins.txt
print-ssh-key:
	@docker exec -it jenkins cat "/home/jenkins/.ssh/gitlab-ssh.pub"
clean-images:
	@docker rmi `docker images -q -f "dangling=true"`
