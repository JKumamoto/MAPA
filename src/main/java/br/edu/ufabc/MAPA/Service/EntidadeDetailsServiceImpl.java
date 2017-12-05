package br.edu.ufabc.MAPA.Service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufabc.MAPA.Model.Entidade;
import br.edu.ufabc.MAPA.Repository.EntidadeRepository;

@Service
public class EntidadeDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private EntidadeRepository entidadeRepository;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		Entidade entidade=entidadeRepository.findByEmail(email);
		HashSet<GrantedAuthority> grantedAuthorities=new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(email));

		return new org.springframework.security.core.userdetails.User(entidade.getEmail(), entidade.getSenha(), grantedAuthorities);
	}

}



