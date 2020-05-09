build:
	@docker-compose -p janus build;
run:
	@docker-compose -p janus up -d
stop:
	@docker-compose -p janus down
stop-clean-data:
	@docker-compose -p janus down -v
clean-images:
	@docker rmi `docker images -q -f "dangling=true"`
