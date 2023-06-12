# Tech Challenge FIAP

### Rodando a aplicação
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