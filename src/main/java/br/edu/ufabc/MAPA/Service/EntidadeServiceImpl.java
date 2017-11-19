package br.edu.ufabc.MAPA.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ufabc.MAPA.Model.Entidade;
import br.edu.ufabc.MAPA.Repository.EntidadeRepository;

@Service("EntidadeService")
public class EntidadeServiceImpl implements EntidadeService{

	@Autowired
	private EntidadeRepository entidadeRepository;

	//@Autowired
	//private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Entidade findByEmail(String email){
		return entidadeRepository.findByEmail(email);
	}

}


