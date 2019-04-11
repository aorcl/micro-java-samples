
# Helidon Example: dept-service-mp

This example implements a simple Hello World REST service using MicroProfile

## Prerequisites

1. Maven 3.5 or newer
2. Java SE 8 or newer
3. Docker 17 or newer (if you want to build and run docker images)
4. Kubernetes minikube v0.24 or newer (if you want to deploy to Kubernetes)
   or access to a Kubernetes 1.7.4 or newer cluster
5. Kubectl 1.7.4 or newer for deploying to Kubernetes
6. Set up SQLite DB and add Departments table as exaplined int his post https://medium.com/@jobinesh/building-a-simple-microservice-using-helidon-microprofile-apis-and-jpa-ac97bb2e45ab

Verify prerequisites
```
java --version
mvn --version
docker --version
minikube version
kubectl version --short
```

## Build

```
mvn package
```

## Start the application

```
java -jar target/dept-service-mp.jar
```

## Exercise the application

```
curl -X POST -H "Content-Type: application/json" \
--data '{"departmentId":30,"departmentName":"Finance","locationId":1700,"managerId":200}' \
http://localhost:8080/departments

curl -X GET http://localhost:8080/departments

```

## Build the Docker Image

```
docker build --no-cache -t dept-service-mp target
```

## Start the application with Docker

```
docker run --rm -p 8080:8080 dept-service-mp:latest

docker run -i -t dept-service-mp:latest /bin/sh
```

Exercise the application as described above

## Deploy the application to Kubernetes

```
kubectl cluster-info                         # Verify which cluster
kubectl get pods                             # Verify connectivity to cluster
kubectl create -f target/app.yaml               # Deploy application
kubectl get service dept-service-mp  # Verify deployed service
```

```
kubectl proxy --port 8888 &
```

pushing an image to OCIR

Create auth token in OCI's user (alexey.dolganov@oracle.com)

log into OCIR from admin's shell:

```
$ docker login lhr.ocir.io
```

user: oracleisv/alexey.dolganov@oracle.com
password: <auth token>

```
$ docker tag dept-service-mp:latest lhr.ocir.io/oracleisv/ado_workshops/dept-service-mp:latest
$ docker push lhr.ocir.io/oracleisv/ado_workshops/dept-service-mp:latest
```
