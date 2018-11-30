package br.edu.iftm.atividadeComplementar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.atividadeComplementar.domains.LancamentoAtividade;
import br.edu.iftm.atividadeComplementar.repositories.LancamentoAtividadeRepository;

@Service
public class LancamentoAtividadeService {

	@Autowired
	private LancamentoAtividadeRepository repository;
	
	public Optional<LancamentoAtividade> buscarCodigo(Long codigo) {
		return repository.findById(codigo);
	}
	
	public List<LancamentoAtividade> buscarTodos() {
		return repository.findAll();
	}
	
	public void salvarAtualizar(LancamentoAtividade lancamento) {
		repository.save(lancamento);
	}
	
	public void excluir(Long codigo) {
		repository.deleteById(codigo);
	}
}