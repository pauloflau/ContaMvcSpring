## Sobre o projeto
O **ContasMVC** é um sistema de gerenciamento de contas a pagar e receber, desenvolvido com **Spring Boot**, **Thymeleaf** e **PostgreSQL**. O objetivo é fornecer uma aplicação simples e eficaz para controle financeiro.

## Tecnologias utilizadas
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Thymeleaf](https://www.thymeleaf.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [Hibernate](https://hibernate.org/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://spring.io/projects/spring-security) (opcional)
- [Bootstrap](https://getbootstrap.com/) (para estilização)

## Pré-requisitos
Antes de rodar a aplicação, certifique-se de ter instalado:
- [JDK 17+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [PostgreSQL](https://www.postgresql.org/download/)

## Configuração do Banco de Dados
1. Crie um banco de dados no PostgreSQL:
   ```sql
   CREATE DATABASE contas_mvc;
   ```
2. Configure as credenciais no `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/contas_mvc
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   ````

## Executando o Projeto
Clone o repositório e navegue até a pasta do projeto:
```sh
git clone https://github.com/seu-usuario/ContasMVC.git
cd ContasMVC
```

Compile e execute a aplicação com:
```sh
mvn spring-boot:run
```
A aplicação estará disponível em: [http://localhost:8080](http://localhost:8080)

## Estrutura do Projeto
```
ContasMVC/
│── src/
│   ├── main/
│   │   ├── java/com/exemplo/contasmvc/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   ├── resources/
│   │   │   ├── templates/ (arquivos Thymeleaf)
│   │   │   ├── static/ (CSS, JS, Imagens)
│── pom.xml
│── README.md
```
## Contato

Caso queira trocar ideias ou sugerir melhorias:  
[LinkedIn](https://www.linkedin.com/in/paulo-flau-43b667382/) 

## Referências
- [Documentação do Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Guia do Thymeleaf](https://www.thymeleaf.org/documentation.html)
- [Documentação do PostgreSQL](https://www.postgresql.org/docs/)

## Licença
Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

