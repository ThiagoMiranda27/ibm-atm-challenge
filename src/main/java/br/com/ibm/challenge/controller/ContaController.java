package br.com.ibm.challenge.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.ibm.challenge.domain.Conta;
import br.com.ibm.challenge.service.ContaService;

@Controller
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	@GetMapping("/conta")
	@ResponseBody
	private List<Conta> todasContas(){
		return contaService.getAllContas();
	}
	
	@GetMapping("/conta/{id}")
	@ResponseBody
	private Optional<Conta> contaCliente(@PathVariable("id") Long id){
		return contaService.getContaById(id);
	}
	
	
}
