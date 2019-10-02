package br.com.ibm.challenge.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.ibm.challenge.domain.CaixaStatus;
import br.com.ibm.challenge.repository.CaixaStatusRepository;

@Service
public class CaixaStatusService {

	@Autowired
	private CaixaStatusRepository caixaStatusRepository;

	CaixaStatus caixa = CaixaStatus.getInstance();

	public boolean abrirCaixa() {

		caixa.setStatusCaixa(true);

		if (this.statusDoCaixa(true).getId() != null) {

			return true;
		}

		return false;
	}

	public boolean fechaCaixa() {

		caixa.setStatusCaixa(false);

		if (this.statusDoCaixa(false).getId() != null) {
			
			return true;
		}
		
		return false;
	}

	public CaixaStatus salvaStatus(CaixaStatus caixa) {
		return caixaStatusRepository.save(caixa);
	}

	public CaixaStatus statusDoCaixa(boolean status) {

		CaixaStatus caixa = CaixaStatus.getInstance();

		caixa.setStatusCaixa(status);
		caixa.setDataStatus(new Date());

		return caixaStatusRepository.save(caixa);
	}
}
