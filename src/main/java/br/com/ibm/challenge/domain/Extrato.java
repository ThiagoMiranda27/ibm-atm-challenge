package br.com.ibm.challenge.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public final class Extrato {

	private static Extrato instance = null;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "id")
	private Conta contaDoCliente;

	private String numeroConta;

	private Double saldo;

	private String tipoTransacao;

	private Double valorTransacao;
	
	public static synchronized Extrato getInstance() {
		if (instance == null) {
			instance = new Extrato();
		}
		return instance;
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

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public double getValorTransacao() {
		return valorTransacao;
	}

	public void setValorTransacao(double valorTransacao) {
		this.valorTransacao = valorTransacao;
	}

	
	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
			return "\n Conta Cliente: " + this.numeroConta + "\n" + " Saldo: " + this.saldo + "\n" +" Tipo Transacao: " 
					+ this.tipoTransacao + "\n" + " Valor Transasao: " + this.valorTransacao + "\n";
	}
	
}
