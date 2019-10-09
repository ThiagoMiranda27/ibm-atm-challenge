package br.com.ibm.challenge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ibm.challenge.domain.CaixaStatus;
import br.com.ibm.challenge.domain.Conta;
import br.com.ibm.challenge.enumerator.TransacaoEnum;

@Service
public class TransferenciaService {
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private CaixaStatusService caixaStatusService;
	
	@Autowired
	private TransacaoService transacaoService;
	
	CaixaStatus caixa = CaixaStatus.getInstance();
	
	public boolean transferir(String contaDoCliente1, String contaDoCliente2, Double valorTransferencia) {
		Optional<Conta> contaCliente1 = contaService.getContaByContaCliente(contaDoCliente1);
		Optional<Conta> contaCliente2 = contaService.getContaByContaCliente(contaDoCliente2);

		if (caixa.isStatusCaixa()) {

			if (contaCliente1.isPresent() && contaCliente2.isPresent()) {
				Conta contaTranferenciaCliente1 = contaCliente1.get();
				Conta contaTranferenciaCliente2 = contaCliente2.get();
				Double saldoCliente1 = contaTranferenciaCliente1.getSaldo();
				Double saldoCliente2 = contaTranferenciaCliente2.getSaldo();
				String numeroConta = contaTranferenciaCliente1.getContaCliente();
				
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
					if (this.transacaoService.gerarTransacao(numeroConta, contaTranferenciaCliente1, null, TransacaoEnum.TRANSFERENCIA.getDescricao(),
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

}
