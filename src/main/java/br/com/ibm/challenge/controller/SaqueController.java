package br.com.ibm.challenge.controller;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ibm.challenge.service.SaqueService;

@RestController
public class SaqueController {
	
	@Autowired
	private SaqueService saqueService;

	@PutMapping("/sacar") 
	@Transactional
	public ResponseEntity<?> sacar(@RequestBody String jsonStr) throws Exception{
			JSONObject jObject = new JSONObject(jsonStr);			
			ArrayList<String> saque = new ArrayList<String>();

			for (int i = 0; i < jObject.getJSONArray("NotaSaque").length(); i++) {
				saque.add(jObject.getJSONArray("NotaSaque").get(i).toString());
			}
			
			saqueService.sacar(jObject.getString("contaCliente"), jObject.getDouble("valorSaque"), saque);
			return ResponseEntity.ok().build();
	}
	
}
