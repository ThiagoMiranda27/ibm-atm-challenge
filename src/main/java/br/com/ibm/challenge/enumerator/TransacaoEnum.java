package br.com.ibm.challenge.enumerator;

public enum TransacaoEnum {
	 SAQUE("saque"),
	 DEPOSITO("deposito"),
	 TRANSFERENCIA("transferencia");
	 
	 private String descricao;
	 
	TransacaoEnum(String descricao) {
	        this.descricao = descricao;
	    }
	 
	    public String getDescricao() {
	        return descricao;
	    }
}
