# Only used for a new deployment
#kubectl create deployment the-communicator --image=eu.gcr.io/the-communicator/the-communicator:latest

# Updates existing deployment with a new image
kubectl set image deployment/the-communicator the-communicator=eu.gcr.io/the-communicator/the-communicator:v1