package br.edu.iftm.atividadeComplementar.domains;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class LancamentoAtividade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull(message="Campo quantidadeHoras precisa ser preenchido")
	private Integer quantidadeHoras;
	
	@NotNull(message="Campo dataInicio precisa ser preenchido")
	@Temporal(value=TemporalType.DATE)
	private Date dataInicio;
	
	@NotNull(message="Campo dataFim precisa ser preenchido")
	@Temporal(value=TemporalType.DATE)
	private Date dataFim;
	
	@ManyToOne
	private Aluno aluno;
	
	@ManyToOne
	private Atividade atividade;
	
	public LancamentoAtividade() {
	}
	
// Super	
	public LancamentoAtividade(Long codigo, Integer quantidadeHoras, Date dataInicio, Date dataFim, Aluno aluno, Atividade atividade) {
		super();
		this.codigo = codigo;
		this.quantidadeHoras = quantidadeHoras;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.aluno = aluno;
		this.atividade = atividade;
	}

// Solicitado	
	public String getSemestreAtividade() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataFim);
		int ano = calendar.get(Calendar.YEAR);
		int mes = calendar.get(Calendar.MONTH);
		
// Janeiro é 0 segundo o java doidão. E o doidão do Mac Lopes kk
		if (mes > 5) {
			return ano + "-2";
		} else {
			return ano + "-1";
		}
	}
	
// Solicitado
	public Integer getHorasAproveitadas() {
		return quantidadeHoras;
		
	}
	

// Auto gerados getters and setters
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setQuantidadeHoras(Integer quantidadeHoras) {
		this.quantidadeHoras = quantidadeHoras;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
}