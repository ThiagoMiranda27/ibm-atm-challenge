package br.com.ibm.challenge.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ibm.challenge.domain.CaixaStatus;
import br.com.ibm.challenge.domain.Conta;
import br.com.ibm.challenge.enumerator.TransacaoEnum;

@Service
public class SaqueService {
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private CaixaStatusService caixaStatusService;
	
	@Autowired
	private TransacaoService transacaoService;
	
	CaixaStatus caixa = CaixaStatus.getInstance();
	
	public boolean sacar(String contaCliente, Double valorSaque, ArrayList<String> NotaSaque) throws Exception {
		Optional<Conta> conta = contaService.getContaByContaCliente(contaCliente);

		if (caixa.isStatusCaixa()) {

			if (conta.isPresent()) {
				Conta contaDoCliente = conta.get();
				Double saldoAtual = contaDoCliente.getSaldo();
				String numeroConta = contaDoCliente.getContaCliente();
				
				Double valorDepoisDoSaque = contaService.valorDoSaque(saldoAtual, valorSaque);

				if (valorDepoisDoSaque > 0) {
					contaDoCliente.setSaldo(valorDepoisDoSaque);

				} else {
					throw new ResponseStatusException(HttpStatus.ACCEPTED, "Saldo insuficiente para realizar o saque");
				}

				contaDoCliente = contaService.salvaTransacao(contaDoCliente);
				caixaStatusService.salvaStatus(caixa);
				
				if (contaDoCliente.getSaldo().equals(valorDepoisDoSaque)) {
					if (this.transacaoService.gerarTransacao(numeroConta, contaDoCliente, null, TransacaoEnum.SAQUE.getDescricao(), valorSaque, null,
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

}
