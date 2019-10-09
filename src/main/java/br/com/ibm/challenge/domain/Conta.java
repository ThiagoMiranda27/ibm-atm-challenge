package br.com.ibm.challenge.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "conta")
public class Conta{
	
	private static Conta instance = null;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	@Column(name="conta_cliente")
	private String contaCliente;
	
	@Column(name="saldo")
	private Double saldo;
	
	public static synchronized Conta getInstance() {
		if (instance == null) {
			instance = new Conta();
		}
		return instance;
	}
	
	public Conta() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContaCliente() {
		return contaCliente;
	}

	public void setContaCliente(String contaCliente) {
		this.contaCliente = contaCliente;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public static void setInstance(Conta instance) {
		Conta.instance = instance;
	}

	
}
