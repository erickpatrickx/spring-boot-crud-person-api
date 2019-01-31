package com.vsaa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vsaa.modelo.Contato;

@Repository
public interface ContatoRepository extends CrudRepository<Contato, Integer>{
	
	List<Contato> findByCpf(String cpf);
}
