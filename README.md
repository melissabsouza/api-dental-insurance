## Dental Insurance API

#### Visão Geral

A API Dental Insurance gerencia dados de clínicas odontológicas, pacientes, dentistas e atendimentos, servindo como base para um aplicativo móvel de armazenamento de informações sobre sinistros odontológicos. Ela permite operações CRUD para essas entidades, oferecendo um maior controle para administração dos dados. Essas informações serão usadas para rastrear sinistros relacionados aos atendimentos.

O projeto tem funcionalidades de front-end utilizando Thymeleaf.

---

Usuario para acessar aplicação:
- user: melissa
- psw: melissa123

Endpoints CRUD:

- /clinicas
- /pacientes
- /dentistas
- /atendimentos
---

#### Arquitetura final
- Backend: Spring Boot + Spring Security pra autenticação e autorização.
    
- Banco de Dados: H2 (in-memory) pra facilitar desenvolvimento e testes rápidos.
    
- Observabilidade: Micrometer + Prometheus + Grafana pra métricas e monitoramento.
    
- IA: pra analisar histórico de atendimentos e detectar fraudes.
    
- Frontend: Views com Thymeleaf

- Aplicação rodando localmente pois deu conflito com o endpoint do keycloak, 
no docker-compose apenas os containers do grafana, prometheus, rabbitmq, fakesmtp e keycloak.



#### Relacionamentos

- Usuários estão associados a clínicas. Cada clínica pode ter um único administrador.
- Endereços são compartilhados entre clínicas, pacientes e dentistas, permitindo reutilização e evitando duplicação de dados.
- Pacientes e dentistas estão associados a clínicas, o que indica que eles estão vinculados a um único local de atendimento.
- Atendimentos estão diretamente relacionados a pacientes e dentistas, permitindo o rastreamento de qual atendimento foi realizado por qual dentista e para qual paciente.
- Telefones podem ser associados a clínicas, pacientes e dentistas, permitindo que cada entidade tenha seus números de contato.

# Equipe do Projeto

## 🚀 Integrantes

### 1. Alissa Silva Cezero - RM 553954
### [GitHub/lissCez](https://github.com/lissCez)

### 2. Melissa Barbosa de Souza - RM 552535
### [GitHub/melissabsouza](https://github.com/melissabsouza)

### 3. Nicolas Paiffer do Carmo - RM 554145
### [GitHub/NPaiffer](https://github.com/NPaiffer) 


