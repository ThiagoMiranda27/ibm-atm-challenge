package br.com.ibm.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.ibm.challenge.enumerator.TipoDepositoEnum;
import br.com.ibm.challenge.service.TransacaoService;

@RestController
@RequestMapping("/atm")
public class TransacaoController {
	
	@Autowired
	TransacaoService transacaoService;
	
	@PutMapping("/depositar")
	public ResponseEntity<?> depositar(@RequestParam String contaDoDeposito,
								@RequestParam Double valorDeposito, @RequestParam TipoDepositoEnum tipoDeposito) throws Exception{
		try {
			transacaoService.depositar(contaDoDeposito, valorDeposito, tipoDeposito);
			return ResponseEntity.ok().build();
		} catch (ResponseStatusException ex) {
			throw ex;
		}
	}
	
}
