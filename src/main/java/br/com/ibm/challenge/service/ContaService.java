package br.com.ibm.challenge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.ibm.challenge.domain.Conta;
import br.com.ibm.challenge.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	public List<Conta> getTodasContas() {
		List<Conta> contas =  new ArrayList<>();
		contas = contaRepository.findAll();
		return contas;
	}
	
	public Optional<Conta> getContaById(Long id) {
		return contaRepository.findById(id);
	}
	
	public Optional<Conta> getContaByContaCliente(String contaCliente) {
		return contaRepository.findByContaCliente(contaCliente);
	}
	
	public Conta salvaTransacao(Conta conta) {
		return contaRepository.save(conta);
	}
	
	public Double valorDoSaque(Double saldoAtual, Double valorSaque) {
		return (saldoAtual - valorSaque);
	}

}
