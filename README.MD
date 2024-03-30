# Projeto Salesforce

# Sumário
- [Objetivo](#objetivo)
- [Requisitos](#requisitos)
- [Instalação](#instalação)
- [Descrição sobre o projeto](#descrição-sobre-o-projeto)

## Objetivo
Nosso projeto tem como objetivo criar um sistema de integração com o banco de dados e com a nossa página do challenge Salesforce

## Requisitos
Para rodar esse projeto é necessário ter instalado:
- Java 21
- Maven

## Instalação
Para instalar o projeto, basta clonar o repositorio e rodar o comando:
```git
 git clone https://github.com/yagoluucas/projeto_salesforce_java.git
```
## Descrção sobre o projeto
O projeto foi desnvolvido utilizando o Java e o Grizzly, um servidor HTTP em Java. O projeto é um sistema de integração com o banco de dados e com a nossa página do challenge salesforce. 
O projeto foi desenvolvido para a atender a nossa 3 sprint do challenge da salesforce, e nele temos os seguintes pacotes:
- Configuration : onde temos a configuração do banco de dados e tambem a configuração do header de acesso ao CORS
- Entities: onde temos as entidades do banco de dados
- Example: onde temos os nossos metodos POST e GET para a integração do banco de dados com a nossa página
- Repository: onde temos nosso crud do banco de dados
- Services: aonde é aplicado a regra de negocio
- Utils: aonde temos a nossa classe de _LOGGER

## Clase Main

Na classe Main, temos o metodo main, que é o metodo principal do projeto, onde é iniciado o servidor HTTP na qual é
criado na url:
'http://localhost:8080/'

## Configuração do banco de dados
Para configurar o banco de dados, foi necessário criar um arquivo de configuração chamado OracleDatabase, lá está disponível
as configurações do banco de dados, como o nome do banco, o usuário e a senha.

Além disso, lá tem um metodo que ao ser chamado, cria uma conexão com o banco de dados e retorna essa conexão, metodo que foi amplamento usado no projeto e nos repositories.

## Configuração do CORS
Para configurar o CORS, foi necessário criar um arquivo de configuração chamado CorsFilter, lá está disponível um metodo que configura o header de acesso ao CORS, permitindo que a nossa página acesse o nosso sistema.

## Interface de LOGGER
Para a interface de LOGGER, foi necessário criar um arquivo de configuração chamado Logger, lá está disponível um metodo que configura o LOGGER, permitindo que o sistema faça logs de informações, erros e etc.

Essas linhas de LOGGER fica armazenada em um arquivo chamado salesforce.log dentro da pasta logs.

## Projeto em execução
Abaixo temos print que mostra o projeto em execução.

### Registro de _LOGGER
![](C:\Users\Yago\programacao\java\sprint3\img\logger.jpeg)(

)




