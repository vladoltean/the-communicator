build_time=$(date +'%Y-%m-%d--%H-%M-%S');

mvn clean install
docker build -t eu.gcr.io/the-communicator/the-communicator .
docker push eu.gcr.io/the-communicator/the-communicator
