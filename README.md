# Caramelo da Sorte

### Equipe
* João Jorge Evangelista Fernandes
* Hugo Leonardo Penha de Sousa
* Matheus David Araujo Santos
### Sobre
Projeto de loteria web que compreende as disciplinas de `PWBII` e `Padrões de Projeto` utilizando Spring Boot + Thymeleaf + Postgres.

### Padrões utilizados
* Null Object + Factory para a model Apostador
* Singleton
* Chain of Responsability
* Observer
* Proxy

### Como rodar

Na pasta `./docker` rodar o comando abaixo para subir um container contendo a base de dados postgres:

~~~sh
$ docker-compose up -d
~~~

Agora é só abrir o projeto no Intellij e rodar. Após rodar pela primeira vez, as tabelas serão geradas no banco, então vá no arquivo `src/main/java/br/edu/ifpb/pweb2/caramelodasorte/security/WebSecurityConfig.java` e comente das linhas 45 à 51. Elas são necessárias na primeira vez que o projeto roda para gerar os usuários de teste no banco. 
