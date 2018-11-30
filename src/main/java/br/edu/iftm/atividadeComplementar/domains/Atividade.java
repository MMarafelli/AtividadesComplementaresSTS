package br.edu.iftm.atividadeComplementar.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Atividade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;

	@NotNull(message="Campo nome precisa ser preenchido")
	private String  nome;
	
	@NotNull(message="Campo maximoAtividadesSemestre precisa ser preenchido")
	private Integer maximoAtividadesSemestre;
	
	@NotNull(message="Campo percentualCargaHoraria precisa ser preenchido")
	private Integer percentualCargaHoraria;
	
	@NotNull(message="Campo percentualPorAtividade precisa ser preenchido")
	private Integer percentualPorAtividade;
	
	@OneToMany(mappedBy="codigo", cascade=CascadeType.ALL)
	private List<LancamentoAtividade> lancamentoAtividade = new ArrayList<>();

	public Atividade() {
	}

	public Atividade(String nome, Long codigo, Integer maximoAtividadesSemestre, Integer percentualCargaHoraria,
			Integer percentualPorAtividade) {
		super();
		this.nome = nome;
		this.codigo = codigo;
		this.maximoAtividadesSemestre = maximoAtividadesSemestre;
		this.percentualCargaHoraria = percentualCargaHoraria;
		this.percentualPorAtividade = percentualPorAtividade;
	}
	
	public Integer getValorLimiteHorasAtividade(Integer totalHorasComplementares) {
		return totalHorasComplementares * percentualPorAtividade / 100;
	}
	
	public Integer getHorasAproveitadasPorAtividade(Integer totalhorasComplementares) {
		return getValorLimiteHorasAtividade(totalhorasComplementares) * percentualCargaHoraria / 100; 
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Integer getMaximoAtividadesSemestre() {
		return maximoAtividadesSemestre;
	}

	public void setMaximoAtividadesSemestre(Integer maximoAtividadesSemestre) {
		this.maximoAtividadesSemestre = maximoAtividadesSemestre;
	}

	public Integer getPercentualCargaHoraria() {
		return percentualCargaHoraria;
	}

	public void setPercentualCargaHoraria(Integer percentualCargaHoraria) {
		this.percentualCargaHoraria = percentualCargaHoraria;
	}

	public Integer getPercentualPorAtividade() {
		return percentualPorAtividade;
	}

	public void setPercentualPorAtividade(Integer percentualPorAtividade) {
		this.percentualPorAtividade = percentualPorAtividade;
	}
	
}