#!/bin/bash

kubectl create secret generic tech-challenge-db-user-secret --from-literal=MARIADB_USER=application_user
kubectl create secret generic tech-challenge-db-password-secret --from-literal=MARIADB_PASSWORD=SENHA123
kubectl create secret generic tech-challenge-db-root-password-secret --from-literal=MARIADB_ROOT_PASSWORD=tech-challenge-password

kubectl apply -f k8s/database 
kubectl apply -f k8s/application 
