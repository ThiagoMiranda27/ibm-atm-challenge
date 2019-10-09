package br.com.ibm.challenge.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ibm.challenge.domain.Conta;
import br.com.ibm.challenge.domain.Transacao;
import br.com.ibm.challenge.repository.TransacaoRepository;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;

	public Transacao gerarTransacao(String numeroConta, Conta contaDoCliente1, Conta contaDoCliente2,
			String transacaoEnum, Double valorTransacao, String tipoDeposito, ArrayList<String> notaDoSaque) {

		Transacao transacao = Transacao.getInstance();

		transacao.setNumeroConta(numeroConta);
		transacao.setTipoTransacao(transacaoEnum);
		transacao.setValorTransacao(valorTransacao);
		transacao.setContaDoCliente1(contaDoCliente1);
		transacao.setContaDoCliente2(contaDoCliente2);
		transacao.setTipoDeposito(tipoDeposito);
		if (notaDoSaque != null) {
			transacao.setNotasSaque(notaDoSaque.toString());
		} else {
			transacao.setNotasSaque(null);
		}
		
		return transacaoRepository.save(transacao);
	}

}
