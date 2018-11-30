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

import br.edu.iftm.atividadeComplementar.domains.Atividade;
import br.edu.iftm.atividadeComplementar.services.AtividadeService;

@RestController
@RequestMapping(value = "/atividades")
public class AtividadeResource {

	@Autowired
	private AtividadeService service;

	@RequestMapping(value = "like/{nome}", method = RequestMethod.GET)
	public ResponseEntity<?> findByNome(@PathVariable String nome) {
		List<Atividade> atividades = service.buscar(nome);
		return ResponseEntity.ok().body(atividades);
	}

	@RequestMapping(value = "{codigo}", method = RequestMethod.GET)
	public ResponseEntity<?> findByCodigo(@PathVariable Long codigo) {
		Optional<Atividade> atividade = service.buscarCodigo(codigo);
		return ResponseEntity.ok().body(atividade);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Atividade> atividades = service.buscarTodos();
		return ResponseEntity.ok().body(atividades);
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@Valid @RequestBody Atividade atividade) {
		service.salvarAtualizar(atividade);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		        .buildAndExpand(atividade.getCodigo()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@Valid @RequestBody Atividade atividade) {
		Optional<Atividade> atividadeOptional = service.buscarCodigo(atividade.getCodigo());

	    if (!atividadeOptional.isPresent()) {
	      return ResponseEntity.notFound().build();
	    }
	    
	 	service.salvarAtualizar(atividade);
	    return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="{ra}")
	public ResponseEntity<?> excluir(@PathVariable Long codigo) {
		try {
			service.excluir(codigo);
			return ResponseEntity.ok(codigo);	
		} catch(EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
		
	}

}