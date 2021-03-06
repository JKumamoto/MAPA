package br.edu.ufabc.MAPA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufabc.MAPA.Model.Tag;

@Repository("TagRepository")
public interface TagRepository extends JpaRepository<Tag, Integer>{
	Tag findById(int id);
	Tag findByNome(String nome);
}

