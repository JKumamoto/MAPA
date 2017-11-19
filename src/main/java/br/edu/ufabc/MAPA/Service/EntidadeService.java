package br.edu.ufabc.MAPA.Service;

import br.edu.ufabc.MAPA.Model.Entidade;

public interface EntidadeService{
	public Entidade findByEmail(String email);
}

