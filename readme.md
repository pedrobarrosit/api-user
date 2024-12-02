
### **Projeto de Automação API RestFul com Rest Assure e JUnit**


Este projeto tem como objetivo automatizar os teste da API "https://reqres.in/" utilizando as biblotecas Java Rest Assured e JUnit, foi aplicado algumas melhorias com assertivas e validação em  cenario ondem é realizado requisições concecutivas e execução de pipeline,
segue uma abordagem do que sera identificado nesta automação.

1. Clone do repositório: O projeto realiza um clone do repositório Git para garantir a utilização da versão mais recente do código.

2. Execução do pipeline: Executa um pipeline pré-configurado para preparar o ambiente de testes.

3. Testes automatizados: Realiza testes automatizados da API, utilizando a biblioteca Rest Assured para fazer requisições HTTP e a JUnit para a criação e execução dos testes.

4. Asserts: Emprega asserts para validar as respostas da API, garantindo a qualidade e a consistência dos serviços.

5. Herança: Utiliza herança para criar testes mais reutilizáveis e organizados.

6. Testes positivos e negativos: Cobre cenários de sucesso e falha para garantir a robustez da API.

7. Testes de carga: Realiza chamadas consecutivas de uma mesma requisição para avaliar o comportamento da API sob carga.

**Pré-requisitos:**

- Java: Versão **<u>1.8.0-202</u>** ou superior.
- Maven: Ferramenta de gerenciamento de dependências.
- Git: Sistema de controle de versão.
- Allure report: Framework utilizado no projeto.

**Execução dos testes:**

- Bash
- mvn test
- mvn allure:serve

### **Contribuições:**

**Contribuições são bem-vindas! Para contribuir, siga estes passos:**

- Fork este repositório.
- Crie um novo branch.
- Faça suas alterações.
- Envie um pull request.

**Considerações:**

Escolha do Rest Assured: O Rest Assured foi escolhido por sua sintaxe intuitiva e facilidade de uso para realizar requisições HTTP e validar respostas.
Escolha do JUnit: O JUnit foi escolhido por ser um framework de testes unitários amplamente utilizado e com uma grande comunidade.
Escolha do Allure Report: O Allure Report foi escolhido por sua capacidade de gerar relatórios visuais e interativos, facilitando a análise dos resultados dos testes.

