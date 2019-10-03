package br.com.ibm.challenge.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public final class CaixaStatus {
	
	private static CaixaStatus instance = null;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private boolean statusCaixa = true;
	
	private Date dataStatus;
	
	
	public static synchronized CaixaStatus getInstance() {
		if (instance == null) {
			instance = new CaixaStatus();
		}
		return instance;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isStatusCaixa() {
		return statusCaixa;
	}

	public void setStatusCaixa(boolean statusCaixa) {
		this.statusCaixa = statusCaixa;
	}

	public Date getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Date dataStatus) {
		this.dataStatus = dataStatus;
	}
		
}
