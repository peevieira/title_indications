# title_indications_api

Aplicação REST API para indicações de piores filmes.

### Contruído com

* [Java versão 11](https://www.java.com/pt-BR/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* * validation
* * security
* [H2 Database Engine](https://www.h2database.com/html/main.html)
* [SpringFox](https://springfox.github.io/springfox/)
* [Apache Maven](https://maven.apache.org/)

### Instalação

1. Clonar o repositório
   ```sh
   git clone https://github.com/peevieira/title_indications.git
   ``` 
### Autenticar Usuário

```sh 
    curl --location --request POST 'http://localhost:8182/auth' \
    --header 'Content-Type: application/json' \    
    --data-raw '{
      "email": "pedro.hviera@live.com",
      "password": "123456"
    }'  
```
### Importar CSV

```sh 
    curl --location --request POST 'http://localhost:8182/csv' \
      --header 'Content-Type: application/json' \
      --header 'Authorization: Bearer token' \
      --data-raw '{
         "path":"String"
      }' 
```
   
### Consultar

```sh 
    curl --location --request GET 'http://localhost:8182/consult' \
      --header 'Content-Type: application/json' \
      --header 'Authorization: Bearer token'
```
# Documentação

[Documentação](http://localhost:8182/swagger-ui.html) {http://localhost:8182/swagger-ui.html}
