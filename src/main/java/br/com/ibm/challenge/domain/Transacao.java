package br.com.ibm.challenge.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.ibm.enumerator.TransacaoEnum;

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cli_id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "id")
	private Conta contaDoCliente;
	
	private TransacaoEnum tipoTransacao;
}
