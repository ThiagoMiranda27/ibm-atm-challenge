package br.com.ibm.challenge.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ibm.challenge.service.DepositoService;

@RestController
public class DepositoController {
	
	@Autowired
	private DepositoService depositoService;
	
	
	@PutMapping("/depositar") 
	@Transactional
	public ResponseEntity<?> depositar(@RequestBody String jsonStr) throws Exception{
			JSONObject jObject = new JSONObject(jsonStr);
			depositoService.depositar(jObject.getString("contaDoDeposito")
					, jObject.getDouble("valorDeposito"), jObject.getString("tipoDeposito"));
			return ResponseEntity.ok().build();
	}

}
