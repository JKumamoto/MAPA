package br.edu.ufabc.MAPA.Service;

import java.util.List;

import br.edu.ufabc.MAPA.Model.Entidade;
import br.edu.ufabc.MAPA.Model.Tag;

public interface EntidadeService{
	void newEntidade(Entidade entidade);
	void saveEntidade(Entidade entidade);
	Entidade findEntidadeByEmail(String email);
	Entidade findEntidadeById(int id);
	void delete(Entidade entidade);
	List<Entidade> findAll();
	List<Entidade> findEntidadeByNome(String nome);
	List<Entidade> findEntidadeByTag(Tag tag);
	void saveTag(Tag tag);
	Tag findTagById(int id);
	Tag findTagByNome(String nome);
}

