kubectl create secret generic tech-challenge-db-user-secret --from-literal=MARIADB_USER=application_user
kubectl create secret generic tech-challenge-db-password-secret --from-literal=MARIADB_PASSWORD=SENHA123
kubectl create secret generic tech-challenge-db-root-password-secret --from-literal=MARIADB_ROOT_PASSWORD=tech-challenge-password

kubectl apply -f ./volumes/storage-class.yaml
kubectl apply -f ./volumes/database-pv.yaml 
kubectl apply -f ./volumes/database-pvc.yaml 

kubectl apply -f ./database/database-deployment.yaml
kubectl apply -f ./database/database-svc.yaml

sleep 90

kubectl apply -f ./application/application-config-map.yaml
kubectl apply -f ./application/application-deployment.yaml
kubectl apply -f ./application/application-svc.yaml