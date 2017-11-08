package br.edu.ufabc.MAPA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufabc.MAPA.Model.Setor;

@Repository("SetorRepository")
public interface SetorRepository extends JpaRepository<Setor, Integer>{
	Setor findByNome(String Nome);

}


