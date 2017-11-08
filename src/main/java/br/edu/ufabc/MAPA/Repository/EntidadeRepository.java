package br.edu.ufabc.MAPA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufabc.MAPA.Model.Entidade;

@Repository("EntidadeRepository")
public interface EntidadeRepository extends JpaRepository<Entidade, Integer>{
	Entidade findByEmail(String email);

}


