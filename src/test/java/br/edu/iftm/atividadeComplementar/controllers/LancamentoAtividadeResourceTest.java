package br.edu.iftm.atividadeComplementar.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

//	@Test
//	public void teste05Post() throws Exception {
//		String url = "/atividades/";
//		
//		LancamentoAtividade obj = new LancamentoAtividade(null, 50, data1, data2, aluno, atividade);
//		
//		this.mvc.perform(post(url)
//				.content(mapper.writeValueAsString(obj))
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isCreated()).andExpect(content().string("")).andDo(MockMvcResultHandlers.print());
//	}
//
//	@Test
//	public void teste05PostIncompletoNome() throws Exception {
//		String url = "/atividades/";
//		
//		ObjectMapper mapper = new ObjectMapper();
//		Atividade obj = new Atividade(null, null, 1, 150, 50);
//
//		this.mvc.perform(post(url)
////	Jackson		.content("{\"maximoAtividadesSemestre\":1,\"percentualCargaHoraria\":150,\"percentualPorAtividade\":50}")
//				.content(mapper.writeValueAsString(obj))
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isBadRequest())
//				.andDo(MockMvcResultHandlers.print());
//	}
//	
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
