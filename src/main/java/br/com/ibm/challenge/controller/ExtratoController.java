package br.com.ibm.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ibm.challenge.service.ExtratoService;

@RestController
public class ExtratoController {

	@Autowired
	private ExtratoService extratoService;

	@GetMapping("/extrato")
	@ResponseBody
	public String extrato() {

		String ex = "";

		ex = extratoService.extrato().toString();

		System.out.println(extratoService.extrato());

		return ex;
	}

}
