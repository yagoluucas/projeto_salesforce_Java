# Estrutura do Projeto
O projeto é composto por várias classes e interfaces, incluindo:
Main: Classe principal do projeto, onde o servidor HTTP Grizzly é iniciado e a aplicação Jersey é configurada.


_BaseEntities: é uma classe base abstrata que fornece parametros que serao utilizados nas outras entidades.

EmailCorporativo, EmailResponsavel, Idioma, Pais, PorteEmpresa, Responsavel, Suporte, TelefoneResponsavel,TesteGratis: São classes públicas necessarias para a aplicação com suas variaveis devidamente setadas.

_BaseRepository: é uma classe base que fornece funcionalidades comuns para os repositórios do sistema

_BaseRepositoryIMPL:classe responsavel por implementar a interface _BaseRepository e define métodos para criar, ler, atualizar e deletar entidades.

ClienteRepository, AtividadeDoSiteRepository, PaisRepository: Estas classes de repositório são responsáveis por realizar operações de CRUD em suas respectivas entidades. Elas são consideradas classes filhas de _BaseRepository

TesteGratisService: Esta classe é um serviço que lida com a lógica de negócios relacionada à entidade TesteGratis.

_Logger: Esta interface é usada para fornecer funcionalidades de log para as classes que a implementam.



