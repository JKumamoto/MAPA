package br.edu.ufabc.MAPA.Service;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufabc.MAPA.Model.Entidade;
import br.edu.ufabc.MAPA.Repository.EntidadeRepository;

@Service("EntidadeService")
public class EntidadeServiceImpl implements EntidadeService, UserDetailsService{

	@Autowired
	private EntidadeRepository entidadeRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Entidade entidade=entidadeRepository.findByEmail(username);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(username));

		return new org.springframework.security.core.userdetails.User(entidade.getEmail(), entidade.getSenha(), grantedAuthorities);
    }
	
	@Override
	public void saveEntidade(Entidade entidade){
		entidade.setSenha(bCryptPasswordEncoder.encode(entidade.getSenha()));
		entidadeRepository.save(entidade);
	}

	@Override
	public Entidade findEntidadeByEmail(String email){
		return entidadeRepository.findByEmail(email);
	}

	@Override
	public Entidade findEntidadeById(int id){
		return entidadeRepository.findById(id);
	}

	@Override
	public List<Entidade> findAll(){
		return entidadeRepository.findAll();
	}

	@Override
	public List<Entidade> findEntidadeByNome(String nome){
		return entidadeRepository.findByNome(nome);
	}

	@Override
	public List<Entidade> findEntidadeByTag(String tag){
		return entidadeRepository.findByTags(tag);
	}

}

