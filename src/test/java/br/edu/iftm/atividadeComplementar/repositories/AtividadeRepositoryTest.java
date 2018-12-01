package br.edu.iftm.atividadeComplementar.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.iftm.atividadeComplementar.domains.Atividade;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtividadeRepositoryTest {
	
	@Autowired
	private AtividadeRepository repository;
	
	@Test
	public void testaAtividadeMonitoria() {
		List<Atividade> c = repository.findByNomeContaining("Monitoria");
		assertThat(c.size()).isGreaterThan(0);
	}
	
	@Test
	public void testaListaAtividadesVazia() {
		List<Atividade> c = repository.findByNomeContaining("Monitoria A");
		assertThat(c.size()).isEqualTo(0);
	}

}
