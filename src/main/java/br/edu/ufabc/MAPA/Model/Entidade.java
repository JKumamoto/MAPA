package br.edu.ufabc.MAPA.Model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

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

	private String descricao;
	private String resumo;

	@Lob
	@Column(name="imagem", columnDefinition="longblob")
	private byte[] imagem;

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

	public String getDescricao(){
		return descricao;
	}

	public void setDescricao(String descricao){
		this.descricao=descricao;
	}

	public String getResumo(){
		return resumo;
	}

	public void setResumo(String resumo){
		this.resumo=resumo;
	}

	public byte[] getImagem(){
		return imagem;
	}

	public void setImagem(byte[] imagem){
		this.imagem=imagem;
	}

	public Set<Tag> getTags(){
		return tags;
	}

	public void setTags(Set<Tag> tags){
		this.tags=tags;
	}

}

