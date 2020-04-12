build_time=$(date +'%Y-%m-%d--%H-%M-%S');
APP_VERSION='v1';

mvn clean install
docker build -t eu.gcr.io/the-communicator/the-communicator:$APP_VERSION .
docker push eu.gcr.io/the-communicator/the-communicator:$APP_VERSION
