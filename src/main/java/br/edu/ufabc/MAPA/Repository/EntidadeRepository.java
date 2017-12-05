package br.edu.ufabc.MAPA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufabc.MAPA.Model.Entidade;

@Repository("EntidadeRepository")
public interface EntidadeRepository extends JpaRepository<Entidade, Integer>{
	Entidade findByEmail(String email);
	Entidade findById(int id);
	List<Entidade> findByNome(String nome);
	List<Entidade> findByTags(String tag);
}


