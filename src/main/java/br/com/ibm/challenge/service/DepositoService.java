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
public class DepositoService {
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private CaixaStatusService caixaStatusService;
	
	@Autowired
	private TransacaoService transacaoService;
	
	CaixaStatus caixa = CaixaStatus.getInstance();
	
	
	public boolean depositar(String contaDoDeposito, Double valorDeposito, String tipoDeposito) throws Exception {
		Optional<Conta> conta = contaService.getContaByContaCliente(contaDoDeposito);

		if (caixa.isStatusCaixa()) {

			if (conta.isPresent()) {

				Conta contaDoCliente1 = conta.get();
				Double saldoAtual = contaDoCliente1.getSaldo();
				String numeroConta = contaDoCliente1.getContaCliente();

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
				
				if (this.transacaoService.gerarTransacao(numeroConta, contaDoCliente1, null, TransacaoEnum.DEPOSITO.getDescricao(), valorDeposito,
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
	
}
