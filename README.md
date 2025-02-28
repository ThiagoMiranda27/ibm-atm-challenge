# Criação do Banco
Criar o banco Pelo postgres. As queries de criação estão no arquivo data.sql

# Documentação da API
Foi implementado o swagger para facilitar na busca das chamadas. Acessar por localhost:8080/swagger-ui.html

# IBM ATM Challenge

Você deve criar uma API de Caixa ATM, onde deve ser possível executar operações de saque, depósito, transferência, extrato e produzir um relatório de fechamento, além de operações e controles que forem necessários.

* Para as operações estarem disponíveis o caixa deve estar aberto, ou seja, deve ser previsto operações de abertura e fechamento de caixa.

### Saque
 - Para operações de saque, devem ser informados os dados necessários como entrada da operação, e retornar as cédulas que serão retiradas do caixa, além de dados complementares em caso de necessidade. 
 
### Depósito
 - Para operações de depósito, devem ser informados os dados necessários como entrada da operação, além do tipo de depósito (DINHEIRO, CHEQUE) e retornar os dados necessários para o comprovante.

### Transferência
 - Para operações de transferência, devem ser informados os dados necessários para a operação das contas origem e destino, com cenários de validação de saldo e possibilidade de agendamento.
 
## Como entregar estes desafios
Você deve *forkar* este projeto e fazer o *push* no seu próprio repositório e enviar o link para o email do recrutador, junto com seu LinkedIn atualizado.

A implementação deve ficar na pasta correspondente ao desafio. Fique à vontade para adicionar qualquer tipo de conteúdo que julgue útil ao projeto, alterar/acrescentar um README com instruções de como executá-lo, etc.

## Critérios de Avaliação
- Clean code;
- Scalability/Performance;
- Flexibility/Extensibility;
- SOC (Separation of Concerns);
- Tratamento de erros e exceções;
- Lógica utilizada para a resolução do exercício.
  
**Observação:**
- Deve-se utilizar linguagem Java em versões 8+;
- A aplicação deve ser em SpringBoot conforme template deste repositório;
- Você não deve fazer um *Pull Request* para este projeto!

## Extras
- Utilização do Docker;
- Programação Funcional;
- Testes unitários ou de integração;
- Explique o processo de resolução do desafio;
- Regras de sugestão de cédulas para melhor distribuição e disponibilidade no caixa;
- Banco de dados, contemplando os dados de contas corrente e informações de cada operação realizada.
