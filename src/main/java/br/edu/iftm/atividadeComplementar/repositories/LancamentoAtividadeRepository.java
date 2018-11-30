package br.edu.iftm.atividadeComplementar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.atividadeComplementar.domains.LancamentoAtividade;


@Repository
public interface LancamentoAtividadeRepository extends JpaRepository<LancamentoAtividade,Long>{
	

}