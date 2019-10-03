package br.com.ibm.challenge.controller;

import java.util.ArrayList;
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
	private TransacaoService transacaoService;
	
 
	
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
			
			ArrayList<String> saque = new ArrayList<String>();
			
			int i = 0;
			for (i = 0; i < jObject.getJSONArray("NotaSaque").length(); i++) {
				saque.add(jObject.getJSONArray("NotaSaque").get(i).toString());
			}
			
			transacaoService.sacar(jObject.getString("contaCliente"), jObject.getDouble("valorSaque"), saque);
			return ResponseEntity.ok().build();
	}
	
	@PutMapping("/transferir") 
	@Transactional
	public ResponseEntity<?> transferir(@RequestBody String jsonStr) throws Exception{
			JSONObject jObject = new JSONObject(jsonStr);
			transacaoService.transferir(jObject.getString("contaDoCliente1"), jObject.getString("contaDoCliente2"),
					jObject.getDouble("valorTransferencia"));
			return ResponseEntity.ok().build();
	}
}
