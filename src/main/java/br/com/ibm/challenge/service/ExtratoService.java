package br.com.ibm.challenge.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ibm.challenge.domain.CaixaStatus;
import br.com.ibm.challenge.domain.Extrato;
import br.com.ibm.challenge.repository.ExtratoRepository;

@Service
public class ExtratoService {

	@Autowired
	private ExtratoRepository extratoRepository;

	@Autowired
	private CaixaStatusService caixaStatusService;

	CaixaStatus caixa = CaixaStatus.getInstance();

	@Transactional
	public List<Extrato> extrato() {

		if (caixa.isStatusCaixa()) {

				List<Extrato> extrato = extratoRepository.extratoPorConta();
				Hibernate.initialize(extrato);

				caixaStatusService.salvaStatus(caixa);

				return extrato;

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status do caixa está fechado");
		}

	}

}
