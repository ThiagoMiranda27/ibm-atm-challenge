package br.com.ibm.challenge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ibm.challenge.domain.Conta;
import br.com.ibm.challenge.domain.Transacao;
import br.com.ibm.challenge.enumerator.TransacaoEnum;
import br.com.ibm.challenge.repository.TransacaoRepository;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Autowired
	private ContaService contaService;

	public boolean depositar(String contaDoDeposito, Double valorDeposito) throws Exception {
		Optional<Conta> conta = contaService.getContaByContaCliente(contaDoDeposito);

		if (conta.isPresent()) {

			Conta contaDoCliente1 = conta.get();
			Double saldoAtual = contaDoCliente1.getSaldo();

			contaDoCliente1.setSaldo((saldoAtual + valorDeposito));
			contaDoCliente1 = contaService.salvaTransacao(contaDoCliente1);

			if (this.gerarTransacao(contaDoCliente1, null, TransacaoEnum.DEPOSITO, valorDeposito).getId() != null) {
				return true;
			} else

				return false;

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não existe");
		}
	}

	public boolean sacar(String contaCliente, Double valorSaque) throws Exception {
		Optional<Conta> conta = contaService.getContaByContaCliente(contaCliente);

		if (conta.isPresent()) {
			Conta contaDoCliente = conta.get();
			Double saldoAtual = contaDoCliente.getSaldo();

			Double valorDepooisDoSaque = contaService.valorDoSaque(saldoAtual, valorSaque);

			if (valorDepooisDoSaque > 0) {
				contaDoCliente.setSaldo(valorDepooisDoSaque);
			} else {
				throw new ResponseStatusException(HttpStatus.ACCEPTED, "Saldo insuficiente para realizar a operação");
			}

			contaDoCliente = contaService.salvaTransacao(contaDoCliente);

			if (contaDoCliente.getSaldo().equals(valorDepooisDoSaque)) {
				if (this.gerarTransacao(contaDoCliente, null, TransacaoEnum.SAQUE, valorSaque).getId() != null) {
					return true;
				}
			}
			return false;
		} else {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta informada não existe");
		}
	}

	public Transacao gerarTransacao(Conta contaDoCliente1, Conta contaDoCliente2, TransacaoEnum transacaoEnum,
			Double valorTransacao) {
		Transacao transacao = new Transacao();
		transacao.setTipoTransacao(transacaoEnum);
		transacao.setValorTransacao(valorTransacao);
		transacao.setContaDoCliente1(contaDoCliente1);
		transacao.setContaDoCliente2(contaDoCliente2);

		return transacaoRepository.save(transacao);
	}

}
