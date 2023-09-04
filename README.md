# Tech Challenge FIAP

## Segunda Entrega - Kubernetes

### Subindo o ambiente
Para essa fase da aplicação é necessário a utilização de um cluster Kubernetes em sistema operacional Linux com acesso a internet. Podendo ser usado a versão “light” do Kubernetes denominada K3S ou sua versão full denominada K8S.

É imprescindível que a aplicação (comando) “kubectl” esteja devidamente configurada e o usuário que fará uso desse comando tenha as permissões necessárias.

É necessário um ajuste no arquivo “k8s/volumes/database-pv.yaml”, na linha 23 (última linha do arquivo). Esse ajuste é a definição do nome do “node” onde o PV será alocado. Para recuperar os nomes dos nodes do cluster, basta usar o comando “kubectl get nodes” e recuperar o nome do node desejado na coluna NAME.

No cluster é necessário que o “Kubernetes Metrics Server” esteja devidamente configurado para que o recurso “Horizontal Pod Autoscaling” (HPA), utilizado no “deployment” da aplicação funcione corretamente.
    
Para executar a aplicação basta executar o arquivo `k8s/init.sh`.

Para testar a execução, faça a seguinte chamada http: `curl --location --request GET 'http://localhost:30100/tech-challenge/health'`.

A aplicação deve responder com:
`Status Code 200 {"status":"OK"}`

### Subindo os pods individualmente
Caso deseje subir somente o pod da aplicação ou o banco individualmente, pode-se usar os manifestos da pasta k8s/pod, lembrando de aplicar as secrets, caso não existam no kubernetes, que estão no arquivo k8s/init.sh


## Primeira Entrega - Docker

### Subindo o ambiente
Para rodar o ambiente completo (banco e aplicação) é necessário apenas rodar o comando `docker-compose up -d`.
Caso já tenha as imagens criadas e queira atualizar o build para uma nova versão, execute o mesmo comando passando o parâmetro --build no final. Ex: `docker-compose up -d --build`.

Dessa forma o docker irá subir 2 containers rodando a aplicação e o banco de dados. Os containers são acessível através da network do docker.

### Rodando a aplicação isoladamente
A imagem utilizada para executar a aplicação foi baseada na JDK 17. Para criar a imagem docker, deve-se abrir um terminal na raiz do projeto e executar o comando:
`docker build -t <TAG_DA_IMAGEM> . `
O dockerfile da aplicação irá copiar todo o conteúdo do repositório para a imagem e irá fazer o build da aplicação, gerando o executável app.jar

O build da aplicação é feito dentro do container para garantir que o ambiente de build seja o mais próximo possível do ambiente no qual a aplicação irá ser executada.

Para rodar a aplicação, basta executar o comando
`docker run -d -p 8080:8080 --name <NOME_DO_CONTAINER> <TAG_DA_IMAGEM>`.
Dessa forma um container será criado já com a aplicação rodando na porta 8080.

Para testar a execução, faça a seguinte chamada http:
`curl --location --request GET 'http://localhost:8080/tech-challenge/health'`.
A aplicação deve responder com um status 200.

### Rodando o banco de dados isoladamente
A imagem utilizada para executar a aplicaçao foi baseada na imagem oficial do mariaDb. Para criar a imagem docker, deve-se abrir um terminal e navegar até a pasta **/database** do projeto e executar o seguinte comando:
`docker build -t <TAG_DA_IMAGEM> . `.

O dockerfile irá configurar a senha do usuário root e também irá copiar o arquivo **setup.sql** para a pasta **/docker-entrypoint-initdb.d** dentro do container. Todos os arquivos **.sql**, **.sh**, etc... localizados na pasta **docker-entrypoint-initdb.d** são executados automaticamente quando o container é iniciado.

No arquivo **setup.sql** estão as configurações básicas necessárias para a aplicação conectar no banco de dados. Ao executar o arquivo, será criado a database utilizada pela aplicação, o usuário/senha que a aplicação utilizará para acessar o banco e as permissões necessárias para que o usuário acesse a database da aplicação.

Para rodar o container, basta executar o comando `docker run -d -p 3606:3606 --name <NOME_DO_CONTAINER> <TAG_DA_IMAGEM>`.