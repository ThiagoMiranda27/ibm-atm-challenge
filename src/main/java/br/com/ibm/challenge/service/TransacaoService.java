package br.com.ibm.challenge.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ibm.challenge.domain.CaixaStatus;
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
	
	@Autowired
	private CaixaStatusService caixaStatusService;

	CaixaStatus caixa = CaixaStatus.getInstance();

	public boolean depositar(String contaDoDeposito, Double valorDeposito, String tipoDeposito) throws Exception {
		Optional<Conta> conta = contaService.getContaByContaCliente(contaDoDeposito);

		if (caixa.isStatusCaixa()) {

			if (conta.isPresent()) {

				Conta contaDoCliente1 = conta.get();
				Double saldoAtual = contaDoCliente1.getSaldo();

				if (tipoDeposito.toLowerCase().equals("dinheiro")) {
					contaDoCliente1.setSaldo((saldoAtual + valorDeposito));
					contaDoCliente1 = contaService.salvaTransacao(contaDoCliente1);
				} else if (tipoDeposito.toLowerCase().equals("cheque")) {
					contaDoCliente1.setSaldo((saldoAtual + valorDeposito));
					contaDoCliente1 = contaService.salvaTransacao(contaDoCliente1);
				} else {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de deposito incorreto!");
				}

				contaDoCliente1 = contaService.salvaTransacao(contaDoCliente1);
				caixaStatusService.salvaStatus(caixa);
				
				if (this.gerarTransacao(contaDoCliente1, null, TransacaoEnum.DEPOSITO.getDescricao(), valorDeposito,
						tipoDeposito.toLowerCase(), null).getId() != null) {
					return true;
				} else

					return false;

			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não existe");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status do caixa está fechado");
		}
	}

	public boolean sacar(String contaCliente, Double valorSaque, ArrayList<String> NotaSaque) throws Exception {
		Optional<Conta> conta = contaService.getContaByContaCliente(contaCliente);

		if (caixa.isStatusCaixa()) {

			if (conta.isPresent()) {
				Conta contaDoCliente = conta.get();
				Double saldoAtual = contaDoCliente.getSaldo();

				Double valorDepoisDoSaque = contaService.valorDoSaque(saldoAtual, valorSaque);

				if (valorDepoisDoSaque > 0) {
					contaDoCliente.setSaldo(valorDepoisDoSaque);

				} else {
					throw new ResponseStatusException(HttpStatus.ACCEPTED, "Saldo insuficiente para realizar o saque");
				}

				contaDoCliente = contaService.salvaTransacao(contaDoCliente);
				caixaStatusService.salvaStatus(caixa);
				
				if (contaDoCliente.getSaldo().equals(valorDepoisDoSaque)) {
					if (this.gerarTransacao(contaDoCliente, null, TransacaoEnum.SAQUE.getDescricao(), valorSaque, null,
							NotaSaque).getId() != null) {
						return true;
					}
				}
				return false;
			} else {

				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta informada não existe");
			}

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status do caixa está fechado");
		}
	}

	public boolean transferir(String contaDoCliente1, String contaDoCliente2, Double valorTransferencia) {
		Optional<Conta> contaCliente1 = contaService.getContaByContaCliente(contaDoCliente1);
		Optional<Conta> contaCliente2 = contaService.getContaByContaCliente(contaDoCliente2);

		if (caixa.isStatusCaixa()) {

			if (contaCliente1.isPresent() && contaCliente2.isPresent()) {
				Conta contaTranferenciaCliente1 = contaCliente1.get();
				Conta contaTranferenciaCliente2 = contaCliente2.get();
				Double saldoCliente1 = contaTranferenciaCliente1.getSaldo();
				Double saldoCliente2 = contaTranferenciaCliente2.getSaldo();

				Double valorDepoisDoSaque = contaService.valorDoSaque(saldoCliente1, valorTransferencia);

				if (valorDepoisDoSaque > 0) {
					contaTranferenciaCliente1.setSaldo(valorDepoisDoSaque);
					contaTranferenciaCliente2.setSaldo(saldoCliente2 + valorTransferencia);
				} else {
					throw new ResponseStatusException(HttpStatus.ACCEPTED,
							"Saldo insuficiente para realizar a transferencia");
				}

				contaTranferenciaCliente1 = contaService.salvaTransacao(contaTranferenciaCliente1);
				contaTranferenciaCliente2 = contaService.salvaTransacao(contaTranferenciaCliente2);
				caixaStatusService.salvaStatus(caixa);

				if (contaTranferenciaCliente1.getSaldo().equals(valorDepoisDoSaque)) {
					if (this.gerarTransacao(contaTranferenciaCliente1, null, TransacaoEnum.TRANSFERENCIA.getDescricao(),
							valorTransferencia, null, null).getId() != null) {
						return true;
					}
				}
				return false;
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta informada não existe");
			}

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status do caixa está fechado");
		}

	}

	public Transacao gerarTransacao(Conta contaDoCliente1, Conta contaDoCliente2, String transacaoEnum,
			Double valorTransacao, String tipoDeposito, ArrayList<String> notaDoSaque) {

		Transacao transacao = new Transacao();
		transacao.setTipoTransacao(transacaoEnum);
		transacao.setValorTransacao(valorTransacao);
		transacao.setContaDoCliente1(contaDoCliente1);
		transacao.setContaDoCliente2(contaDoCliente2);
		transacao.setTipoDeposito(tipoDeposito);
		if (notaDoSaque != null) {
			transacao.setNotasSaque(notaDoSaque.toString());
		}

		return transacaoRepository.save(transacao);
	}

}
