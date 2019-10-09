package br.com.ibm.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ibm.challenge.domain.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
