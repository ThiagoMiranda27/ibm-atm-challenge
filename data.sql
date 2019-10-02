-- Banco Postgree: criar o banco ATM e criar a tabela conta dando os INSERTS a baixo como exemplo

CREATE DATABASE ATM

CREATE TABLE conta (
	id SERIAL PRIMARY KEY,
	conta_cliente VARCHAR NOT NULL,
	saldo numeric NOT NULL
)

INSERT INTO conta(conta_cliente, saldo)
VALUES
('2134566547', 500.00),
('5082182723', 1000.00),
('4322656457', 3000.00)