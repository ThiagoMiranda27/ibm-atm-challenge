package br.com.ibm.challenge.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ibm.challenge.service.TransferenciaService;

@RestController
public class TransferenciaController {
	
	@Autowired
	private TransferenciaService transferenciaService;

	@PutMapping("/transferir") 
	@Transactional
	public ResponseEntity<?> transferir(@RequestBody String jsonStr) throws Exception{
			JSONObject jObject = new JSONObject(jsonStr);
			transferenciaService.transferir(jObject.getString("contaDoCliente1"), jObject.getString("contaDoCliente2")
					,jObject.getDouble("valorTransferencia"));
			return ResponseEntity.ok().build();
	}
	
}
