
# kubectl apply -f volumes/database-pv.yaml
# kubectl apply -f volumes/database-pvc.yaml
# kubectl apply -f deployment/database-deployment.yaml


kubectl create secret generic tech-challenge-db-user-secret --from-literal=MARIADB_USER=application_user
kubectl create secret generic tech-challenge-db-password-secret --from-literal=MARIADB_PASSWORD=SENHA123
kubectl create secret generic tech-challenge-db-root-password-secret --from-literal=MARIADB_ROOT_PASSWORD=tech-challenge-password