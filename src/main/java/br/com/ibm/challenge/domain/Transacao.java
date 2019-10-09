package br.com.ibm.challenge.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public final class Transacao {

	private static Transacao instance = null;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "id")
	private Conta contaDoCliente1;

	@OneToOne
	@JoinColumn(name = "id")
	private Conta contaDoCliente2;
	
	private String numeroConta;

	private String tipoDeposito;

	private String notasSaque = "";

	private String tipoTransacao;

	private double valorTransacao;
	
	public static synchronized Transacao getInstance() {
		if (instance == null) {
			instance = new Transacao();
		}
		return instance;
	}
	
	public Transacao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public Conta getContaDoCliente1() {
		return contaDoCliente1;
	}

	public void setContaDoCliente1(Conta contaDoCliente1) {
		this.contaDoCliente1 = contaDoCliente1;
	}

	public Conta getContaDoCliente2() {
		return contaDoCliente2;
	}

	public void setContaDoCliente2(Conta contaDoCliente2) {
		this.contaDoCliente2 = contaDoCliente2;
	}

	public double getValorTransacao() {
		return valorTransacao;
	}

	public void setValorTransacao(double valorTransacao) {
		this.valorTransacao = valorTransacao;
	}

	public String getTipoDeposito() {
		return tipoDeposito;
	}

	public void setTipoDeposito(String tipoDeposito) {
		this.tipoDeposito = tipoDeposito;
	}

	public String getNotasSaque() {
		return notasSaque;
	}

	public void setNotasSaque(String notasSaque) {
		this.notasSaque = notasSaque;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	
}
