package br.edu.ufabc.MAPA.Service;

import java.util.List;

import br.edu.ufabc.MAPA.Model.Entidade;

public interface EntidadeService{
	void saveEntidade(Entidade entidade);
	Entidade findEntidadeByEmail(String email);
	Entidade findEntidadeById(int id);
	List<Entidade> findAll();
	List<Entidade> findEntidadeByNome(String nome);
	List<Entidade> findEntidadeByTag(String tag);
}

