package br.com.ibm.challenge.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.com.ibm.challenge.domain.Extrato;

public interface ExtratoRepository extends JpaRepository<Extrato, Long>{

	@Query(value="select c.id, t.numero_conta, c.saldo, t.tipo_transacao, t.valor_transacao from transacao t, conta c\r\n" + 
			"where t.numero_conta = c.conta_cliente", nativeQuery = true)
	public List<Extrato> extratoPorConta(); 
	
}
