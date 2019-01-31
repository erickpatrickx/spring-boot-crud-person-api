package com.vsaa.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vsaa.modelo.Contato;
import com.vsaa.repository.ContatoRepository;
import com.vsaa.util.Constantes;

@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private ContatoRepository repositoryContato;
	
	@Override
	public UserDetails loadUserByUsername(String cpf)	throws UsernameNotFoundException, DataAccessException {
      List<GrantedAuthority> listGrantAuthority = new ArrayList<GrantedAuthority>();
      List<Contato> contatos = repositoryContato.findByCpf(cpf);
      Contato contato = new Contato();
      if(contatos.size() > 0) {
    	  contato =  contatos.get(0);
      }
      SimpleGrantedAuthority authority = new SimpleGrantedAuthority(Constantes.PERMISSAO_CONTATO);
      List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
      updatedAuthorities.add(authority);
      
      return new User(contato.getNome(), contato.getPassword(), 
    		  updatedAuthorities);
	}
}