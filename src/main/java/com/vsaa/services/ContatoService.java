package com.vsaa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.vsaa.modelo.Contato;
import com.vsaa.modelo.Role;
import com.vsaa.repository.ContatoRepository;
import com.vsaa.repository.RoleRepository;

@Service
public class ContatoService {
	@Autowired
	private ContatoRepository repository;

	@Autowired
	private RoleRepository repositoryRole;

	/**
	 * Faz a busca de Role para transforma-lo em managed pelo JPA 
	 * e evitar erro de Detached quando for atualizar o usuário
	 * @param id do Role
	 * @return Role 
	 */
	public Role buscaRole(int id) {
		return repositoryRole.findOne(id);
	}

	/**
	 * Insere um usuário
	 * @param contato 
	 */
	public void inserir(Contato contato) {
		repository.save(contato);
	}

	public Contato buscarPorEmail(String email, String senha) {
		// TODO implementar busca por e-mail e senha
		return null;
	}
	
	
	/**
	 * Dada uma lista de usuários checa se não está vazia
	 * @param contatos Lista de usuários 
	 * @return Lista de usuários não vazia | null
	 */
	public List<Contato> checaListaNaoVazia(List<Contato> contatos) {
		if (contatos.size() > 0) {
			return contatos;
		} else {
			return null;
		}
	}

	/**
	 * Lista todos os usuários da lista de usuários
	 * @return List<Contato> lista de usuários
	 */
	public List<Contato> listar() {
		Iterable<Contato> contatos = repository.findAll();

		List<Contato> listaContatos = new ArrayList<>();

		for (Contato contato : contatos) {
			listaContatos.add(contato);
		}
		return listaContatos;
	}

	/**
	 * Lista todos os usuários da lista
	 * @return Iterable<Contato>
	 */
	public Iterable<Contato> obterTodos() {
		Iterable<Contato> contatos = repository.findAll();
		return contatos;
	}


	/**
	 * Remove um usuário selecionado
	 * @param u usuário
	 */
	public void remover(Contato contato) {
		repository.delete(contato);
	}

	/**
	 * Remove um usuário selecionado
	 * @param id do usuário
	 */
	public void remover(int id) {
		repository.delete(this.buscarPorId(id));
	}

	/**
	 * Altera um usuário pelo seu identificador
	 * 
	 * @param id
	 * @param nome
	 * @param login
	 * @param email
	 * @param senha
	 * @param imagemPath
	 */
	public void alterar(Contato contato,int id) {
		Contato contatoSave = this.buscarPorId(id);
		BeanUtils.copyProperties(contato, contatoSave);
		contatoSave.setId(id);
		repository.save(contato);
	}

	/**
	 * Faz a busca de um usuário por id
	 * 
	 * @param id do usuário      
	 * @return Contato
	 */
	public Contato buscarPorId(int id) {
		return repository.findOne(id);
	}

	public List<Contato> buscarPorCPF(String cpf) {
		return repository.findByCpf(cpf);
		
	}

}