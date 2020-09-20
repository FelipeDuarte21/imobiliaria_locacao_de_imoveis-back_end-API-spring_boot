package br.com.felipeDuarte.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "locacao")
public class Locacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idLocacao;
	
	@NotNull(message = "Informe a data da locação")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@NotNull(message = "Informe o tempo da locação")
	@NotBlank(message = "Informe o tempo da locação")
	@Length(max = 5, message = "Informe o tempo em até {max} caracteres")
	private String tempo;
	
	@NotNull(message = "Informe a data de início da locação")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	
	@NotNull(message = "Informe o data de término da locação")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataTermino;
	
	@NotNull(message = "Informe o valor da locação")
	@Column(precision = 2)
	private Double valor;
	
	@NotNull(message = "Informe o imóvel da locação")
	@OneToOne
	@JoinColumn(name = "id_Imovel")
	private Imovel imovel;
	
	@NotNull(message = "Informe o inquilino")
	@ManyToOne
	@JoinColumn(name = "id_Inquilino")
	private Pessoa inquilino;
	
	@JsonIgnore
	@OneToMany(mappedBy = "locacao")
	private List<Aluguel> alugueis = new ArrayList<>();
	
	public Locacao() {
		
	}

	public Locacao(Integer idLocacao,String tempo, Date data, Date dataInicio, Date dataTermino, 
	Double valor,Imovel imovel,Pessoa inquilino) {
		super();
		this.idLocacao = idLocacao;
		this.data = data;
		this.tempo = tempo;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.valor = valor;
		this.imovel = imovel;
		this.inquilino = inquilino;
	}

	public Integer getIdLocacao() {
		return idLocacao;
	}

	public void setIdLocacao(Integer idLocacao) {
		this.idLocacao = idLocacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Pessoa getInquilino() {
		return inquilino;
	}

	public void setPessoa(Pessoa inquilino) {
		this.inquilino = inquilino;
	}

	public List<Aluguel> getAlugueis() {
		return alugueis;
	}

	public void setAlugueis(List<Aluguel> alugueis) {
		this.alugueis = alugueis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLocacao == null) ? 0 : idLocacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locacao other = (Locacao) obj;
		if (idLocacao == null) {
			if (other.idLocacao != null)
				return false;
		} else if (!idLocacao.equals(other.idLocacao))
			return false;
		return true;
	}
	
}
