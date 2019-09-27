package br.com.ibm.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ibm.challenge.repository.TransacaoRepository;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private ContaService contaService;
	
	
	public boolean depositar(String contaDoCliente, Double valorDeposito) {
		Conta conta = contaService.getContaByContaCliente(contaDoCliente);
		

	}
}
