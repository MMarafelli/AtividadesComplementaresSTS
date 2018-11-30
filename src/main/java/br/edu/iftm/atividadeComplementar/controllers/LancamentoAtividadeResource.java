package br.edu.iftm.atividadeComplementar.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.iftm.atividadeComplementar.domains.LancamentoAtividade;
import br.edu.iftm.atividadeComplementar.services.LancamentoAtividadeService;

@RestController
@RequestMapping(value = "/lancamentoAtividades")
public class LancamentoAtividadeResource {

	@Autowired
	private LancamentoAtividadeService service;

	@RequestMapping(value = "{codigo}", method = RequestMethod.GET)
	public ResponseEntity<?> findByRa(@PathVariable Long codigo) {
		Optional<LancamentoAtividade> lancamentos = service.buscarCodigo(codigo);
		return ResponseEntity.ok().body(lancamentos);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<LancamentoAtividade> lancamentos = service.buscarTodos();
		return ResponseEntity.ok().body(lancamentos);
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@Valid @RequestBody LancamentoAtividade lancamento) {
		service.salvarAtualizar(lancamento);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		        .buildAndExpand(lancamento.getCodigo()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@Valid @RequestBody LancamentoAtividade lancamento) {
		Optional<LancamentoAtividade> lancamentoOptional = service.buscarCodigo(lancamento.getCodigo());

	    if (!lancamentoOptional.isPresent()) {
	      return ResponseEntity.notFound().build();
	    }
	    
	 	service.salvarAtualizar(lancamento);
	    return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="{ra}")
	public ResponseEntity<?> excluir(@PathVariable Long ra) {
		try {
			service.excluir(ra);
			return ResponseEntity.ok(ra);	
		} catch(EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
		
	}

}