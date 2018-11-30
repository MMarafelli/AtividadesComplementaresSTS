package br.edu.iftm.atividadeComplementar.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.iftm.atividadeComplementar.domains.Aluno;
import br.edu.iftm.atividadeComplementar.domains.Atividade;
import br.edu.iftm.atividadeComplementar.domains.LancamentoAtividade;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LancamentoAtividadeResourceTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void teste01RequisicaoIdOk() throws Exception {
		String url = "/lancamentoAtividades/1";
		this.mvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Monitoria")));
	}

	@Test
	public void teste01RequisicaoIdNOk() throws Exception {
		String url = "/lancamentoAtividades/66";
		this.mvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("null")));
	}

	@Test
	public void teste02RequisicaoFindAll() throws Exception {
		String url = "/lancamentoAtividades?page=0&size=10";
		this.mvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("nome")));
	}

	@Test
	public void teste03RequisicaoDelete() throws Exception {
		String url = "/lancamentoAtividades/1";
		this.mvc.perform(delete(url))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("1")));
	}

	@Test
	public void teste03RequisicaoDeleteFalha() throws Exception {
		String url = "/lancamentoAtividades/666";
		this.mvc.perform(delete(url))
				.andExpect(content().string(containsString("")));
	}

	@Test
	public void teste05Post() throws Exception {
		String url = "/atividades/";
		
		ObjectMapper mapper = new ObjectMapper();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date   date1       = format.parse ( "2018-06-18" ); 
		Date   date2       = format.parse ( "2018-12-18" );
		
		Aluno aluno = new Aluno(11113L,"breno");
		
		Atividade atividade = new Atividade("Disciplinas Extracurriculares", 2L, 1, 50, 40);
		
		LancamentoAtividade obj = new LancamentoAtividade(null, 50, date1, date2, aluno, atividade);
		
		List<LancamentoAtividade> atividades = new ArrayList<>();
		
		atividades.add(obj);
		
		aluno.setAtividades(atividades);
		
		this.mvc.perform(post(url)
				.content(mapper.writeValueAsString(obj))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().string(""))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void teste05PostIncompletoQuantidadeHoras() throws Exception {
		String url = "/atividades/";
		
		ObjectMapper mapper = new ObjectMapper();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date   date1       = format.parse ( "2018-06-18" ); 
		Date   date2       = format.parse ( "2018-12-18" );
		
		Aluno aluno = new Aluno(11113L,"breno");
		
		Atividade atividade = new Atividade("Disciplinas Extracurriculares", 2L, 1, 50, 40);
		
		LancamentoAtividade obj = new LancamentoAtividade(null, null, date1, date2, aluno, atividade);
		
		this.mvc.perform(post(url)
				.content(mapper.writeValueAsString(obj))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andDo(MockMvcResultHandlers.print());
	}
	
//	public void teste05PostIncompletoCargaHoraria() throws Exception {
//		String url = "/atividades/";
//		
//		ObjectMapper mapper = new ObjectMapper();
//		Atividade obj = new Atividade("Algebra", null, null, 150, 50);
//
//		this.mvc.perform(post(url)
////	Jackson		.content("{\"nome\":\"Algebra\",\"maximoAtividadesSemestre\":1,\"percentualPorAtividade\":50}")
//				.content(mapper.writeValueAsString(obj))
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isBadRequest())
//				.andDo(MockMvcResultHandlers.print());
//	}
//	
//	@Test
//	public void teste06PutOk() throws Exception {
//		String url = "/atividades/";
//		
//		ObjectMapper mapper = new ObjectMapper();
//		Atividade obj = new Atividade("Teste nome", 5L, 2, 20, 40);
//        
//		this.mvc.perform(put(url)
//				.content(mapper.writeValueAsString(obj))
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isNoContent())
//		        .andDo(MockMvcResultHandlers.print());
//	}
//	
//	@Test
//	public void teste12PutIncompletoNome() throws Exception {
//		String url = "/atividades/";
//		
//		ObjectMapper mapper = new ObjectMapper();
//		Atividade obj = new Atividade(null, 5L, 2, 20, 40);
//		
//		this.mvc.perform(put(url)
//		.content(mapper.writeValueAsString(obj))
//		.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(status().isBadRequest())
//		.andDo(MockMvcResultHandlers.print());
//	}

}
