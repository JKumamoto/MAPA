package br.edu.ufabc.MAPA.Model;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity // Hibernate cria uma tabela dessa classe
public class Entidade{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private int id;

	@NotEmpty(message = "*Nome da instituicao")
	private String nome;

	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;

	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	@Transient
	private String senha;

	@Temporal(TemporalType.DATE)
	private Calendar fundacao;

	private String setor;
	private boolean reconhecimento_institucional;
	private String descricao;
	private String comunicao_externa;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "entidade_tags", joinColumns = @JoinColumn(name = "entidade_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<Tag> tags;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id=id;
	}

	public String getNome(){
		return nome;
	}

	public void setNome(String nome){
		this.nome=nome;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getSenha(){
		return senha;
	}

	public void setSenha(String senha){
		this.senha=senha;
	}

	public Calendar getFundacao(){
		return fundacao;
	}

	public void setFundacao(Calendar fundacao){
		this.fundacao=fundacao;
	}

	public String getSetor(){
		return setor;
	}

	public void setSetor(String setor){
		this.setor=setor;
	}

	public boolean getReconhecimento_institucional(){
		return reconhecimento_institucional;
	}

	public void setReconhecimento_institucional(boolean reconhecimento_institucional){
		this.reconhecimento_institucional=reconhecimento_institucional;
	}

	public String getDescricao(){
		return descricao;
	}

	public void setDescricao(String descricao){
		this.descricao=descricao;
	}

	public String getComunicao_externa(){
		return comunicao_externa;
	}

	public void setComunicao_externa(String comunicao_externa){
		this.comunicao_externa=comunicao_externa;
	}

	public Set<Tag> getTags(){
		return tags;
	}

	public void setTags(Set<Tag> tags){
		this.tags=tags;
	}
	
}

