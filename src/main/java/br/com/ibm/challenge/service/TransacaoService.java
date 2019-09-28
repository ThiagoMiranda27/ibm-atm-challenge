package br.com.ibm.challenge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ibm.challenge.domain.Conta;
import br.com.ibm.challenge.domain.Transacao;
import br.com.ibm.challenge.enumerator.TipoDepositoEnum;
import br.com.ibm.challenge.enumerator.TransacaoEnum;
import br.com.ibm.challenge.repository.TransacaoRepository;


@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private ContaService contaService;
	
	 
	public boolean depositar(String contaDoDeposito, Double valorDeposito, TipoDepositoEnum tipoDeposito) throws Exception{
		Optional<Conta> conta = contaService.getContaByContaCliente(contaDoDeposito);

		if(conta.isPresent()){
			Conta contaDoCliente1 = conta.get();
			Double saldoAtual = contaDoCliente1.getSaldo();

			if(tipoDeposito == TipoDepositoEnum.DINHEIRO) {
				contaDoCliente1.setSaldo((saldoAtual + valorDeposito));
				contaDoCliente1 = contaService.salvaTransacao(contaDoCliente1);
				
			} else if(tipoDeposito == TipoDepositoEnum.CHEQUE) {
				contaDoCliente1.setSaldo((saldoAtual + valorDeposito));
				contaDoCliente1 = contaService.salvaTransacao(contaDoCliente1);
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "tipo de deposito não existe");
			}
			
			if (this.gerarTransacao(contaDoCliente1, null, TransacaoEnum.DEPOSITO, TipoDepositoEnum.DINHEIRO
					, valorDeposito).getId() != null) {
				return true;
			} else if (this.gerarTransacao(contaDoCliente1, null, TransacaoEnum.DEPOSITO, TipoDepositoEnum.CHEQUE
					, valorDeposito).getId() != null) {
				return true;
			} else
				
			return false;
			
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não existe");
		}
	}


	public Transacao gerarTransacao(Conta contaDoCliente1, Conta contaDoCliente2, TransacaoEnum transacaoEnum, TipoDepositoEnum tipoDeposito, Double valorTransacao){
		Transacao transacao = new Transacao();
		transacao.setTipoTransacao(transacaoEnum);
		transacao.setTipoDeposito(tipoDeposito);
		transacao.setValorTransacao(valorTransacao);
		transacao.setContaDoCliente1(contaDoCliente1);
		transacao.setContaDoCliente2(contaDoCliente2);
		

		return transacaoRepository.save(transacao);
	}
	
	
}
