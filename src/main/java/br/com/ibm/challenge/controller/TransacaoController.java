package br.com.ibm.challenge.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ibm.challenge.service.TransacaoService;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
	
	@Autowired
	TransacaoService transacaoService;
	
	@PutMapping("/depositar") 
	@Transactional
	public ResponseEntity<?> depositar(@RequestBody String jsonStr) throws Exception{
			JSONObject jObject = new JSONObject(jsonStr);
			transacaoService.depositar(jObject.getString("contaDoDeposito")
					, jObject.getDouble("valorDeposito"), jObject.getString("tipoDeposito"));
			return ResponseEntity.ok().build();
	}
	
	@PutMapping("/sacar") 
	@Transactional
	public ResponseEntity<?> sacar(@RequestBody String jsonStr) throws Exception{
			JSONObject jObject = new JSONObject(jsonStr);
			transacaoService.sacar(jObject.getString("contaCliente"), jObject.getDouble("valorSaque"));
			return ResponseEntity.ok().build();
	}
	
}
