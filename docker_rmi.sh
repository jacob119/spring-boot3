docker rm -f $(docker ps -aq --filter ancestor=spring3-demo:0.0.1)
docker rmi spring3-demo:0.0.1  
