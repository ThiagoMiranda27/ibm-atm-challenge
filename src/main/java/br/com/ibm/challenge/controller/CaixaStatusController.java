package br.com.ibm.challenge.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import br.com.ibm.challenge.service.CaixaStatusService;

@RestController
@RequestMapping("/caixa")
public class CaixaStatusController {

	@Autowired
	CaixaStatusService caixaStatusService;

	@PutMapping("/abrir")
	@Transactional
	public void abreCaixa() {
		caixaStatusService.abrirCaixa();
	}

	@PutMapping("/fechar")
	@Transactional
	public void fecharCaixa() {
		caixaStatusService.fechaCaixa();
	}

}
