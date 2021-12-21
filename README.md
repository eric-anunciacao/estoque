# [Cadastro de Produtos](https://github.com/eric-anunciacao/estoque)

Este é um MVP de cadastro de produtos, com a possibilidade de:

* Importação de novos produtos via arquivo CSV;
* Consulta para saber se o arquivo foi importado com sucesso;
* Atualização de produtos existentes;
* Listar os produtos cadastrados;
* Remover produtos;


## Design

Essa API foi desenvolvida utilizando Java 11, Spring Boot, MySQL e IBM MQ. 

O conceito de design de software utilizado na construção da aplicação foi Clean Architecture. Com isso, é possível identificar alguns conceitos de SOLID, tais como:
* **Single Responsibility Principle (SRP)**, onde cada classe deve ter apenas um motivo para mudar. É possível identificar esse princípio nas classes que implementam os Casos de Uso;
* **Interface Segregation Principle (ISP)**, onde as interfaces criadas foram refinadas para que sejam específicas do cliente. É possível ver esse princípio nas interfaces de Caso de Uso, onde cada Caso de Uso possui apenas um método público;
* **Dependency Inversion Principle (DIP)**, onde módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender da abstração. Esse princípio pode ser visto na comunicação entre as camadas do projeto, que se comunicam umas com as outras através do contrato disponibilizado pelas suas interfaces.

Como boas práticas de desenvolvimento, é possível identificar:
* Todas as classes, métodos, atributos e variáveis foram criados com nomes que revelam a intenção;
* A maioria dos métodos não possuem mais do que 10 linhas de código;
* A aplicação foi desenvolvida seguindo o TDD;

Os testes unitários e integrados estão sendo executados com o banco de dados H2 e possuem uma cobertura de código de 95%. Em alguns casos, foi utilizado o Mockito para simular o retorno dos métodos.

## Pré-requisitos

Para executar a aplicação, é necessário ter configurado na máquina as seguintes ferramentas:

* Maven
* Java 11
* Docker

## Execução da aplicação

Utilizando o Docker, é possível criar a estrutura de banco de dados e levantar a aplicação seguindo os passos abaixo:

1. Construir a aplicação com Maven executando o comando `mvn clean install`
2. Acessar o diretório principal do projeto (onde encontra-se o arquivo `docker-compose.yaml`)
3. Executar o comando `docker-compose up --build` para iniciar a aplicação
4. Ao concluir o passo anterior, você poderá testar o acesso da aplicação utilizando a Collection do Postman `estoque.postman_collection.json` que encontra-se no diretório raiz do projeto.