package br.edu.ufabc.MAPA.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufabc.MAPA.Model.Entidade;
import br.edu.ufabc.MAPA.Model.Tag;

@Repository("EntidadeRepository")
public interface EntidadeRepository extends JpaRepository<Entidade, Integer>{
	Entidade findByEmail(String email);
	Entidade findById(int id);
	void delete(Entidade entidade);
	List<Entidade> findByNome(String nome);
	List<Entidade> findByTags(Tag tag);
}


