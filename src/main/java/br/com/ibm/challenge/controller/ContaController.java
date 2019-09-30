package br.com.ibm.challenge.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.ibm.challenge.domain.Conta;
import br.com.ibm.challenge.service.ContaService;

@RequestMapping("/")
@RestController
public class ContaController {

	@Autowired
	private ContaService contaService;

	@GetMapping("/contas")
	@ResponseBody
	private List<Conta> todasContas() {
		return contaService.getTodasContas();
	}

	@GetMapping("/id/{id}")
	@ResponseBody
	private Optional<Conta> returnIdContaCliente(@PathVariable Long id) {
		return contaService.getContaById(id);
	}
	
	@GetMapping("/conta/{contaCliente}") 
	@ResponseBody
	private ResponseEntity<Conta> returnContaCliente(@PathVariable String contaCliente) {
		try {
			Optional<Conta> conta = contaService.getContaByContaCliente(contaCliente);

			if (conta.isPresent()) {
				return ResponseEntity.ok(conta.get());
			}

			return ResponseEntity.notFound().build();
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	@PostMapping("/contas")
	@Transactional
	private ResponseEntity<Long> novaConta(@RequestBody Conta conta){
		try{
			contaService.salvaTransacao(conta);
			return new ResponseEntity<>(conta.getId(), HttpStatus.CREATED);
		} catch (Exception e) {
			throw e;
		}
	}



}
