package br.edu.iftm.atividadeComplementar.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.iftm.atividadeComplementar.domains.LancamentoAtividade;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LancamentoAtividadeRepositoryTest {
	
	@Autowired
	private LancamentoAtividadeRepository repository;
	
	@Test
	public void testaAtividadeMonitoria() {
		List<LancamentoAtividade> c = repository.findByCodigoContaining(1L);
		assertThat(c.size()).isGreaterThan(0);
	}
	
	@Test
	public void testaListaAtividadesVazia() {
		List<LancamentoAtividade> c = repository.findByCodigoContaining(20L);
		assertThat(c.size()).isEqualTo(0);
	}

}
