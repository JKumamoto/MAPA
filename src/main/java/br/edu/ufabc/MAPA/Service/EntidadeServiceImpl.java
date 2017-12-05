package br.edu.ufabc.MAPA.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ufabc.MAPA.Model.Entidade;
import br.edu.ufabc.MAPA.Repository.EntidadeRepository;

@Service("EntidadeService")
public class EntidadeServiceImpl implements EntidadeService{

	@Autowired
	private EntidadeRepository entidadeRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void saveEntidade(Entidade entidade){
		entidade.setSenha(bCryptPasswordEncoder.encode(entidade.getSenha()));
		entidadeRepository.save(entidade);
	}

	public Entidade findEntidadeByEmail(String email){
		return entidadeRepository.findByEmail(email);
	}

	public Entidade findEntidadeById(int id){
		Entidade e=entidadeRepository.findById(id);
		return e;
	}

	public List<Entidade> findEntidadeByNome(String nome){
		return entidadeRepository.findByNome(nome);
	}

	public List<Entidade> findEntidadeByTag(String tag){
		return entidadeRepository.findByTags(tag);
	}

}


