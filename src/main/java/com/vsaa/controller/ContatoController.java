package com.vsaa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vsaa.modelo.Contato;
import com.vsaa.modelo.Role;
import com.vsaa.services.ContatoService;
import com.vsaa.util.FileSaver;
import com.vsaa.util.GeradorSenha;
import com.vsaa.util.ManipulaPermissoes;

@Controller
public class ContatoController {
private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContatoService contatoService;
	
	@Autowired
	private FileSaver fileSaver;
	
	/**
	 * Contrutor
	 */
	public ContatoController(){
	}
	
	
	/**
	 * Carrega o formulário de busca de usuários
	 * @param session Session do usuário da aplicação
	 * @return página TelaBuscarContato.jsp | Home.jsp
	 */
	@RequestMapping(value = "/buscarContato", method = RequestMethod.GET)
	public String carregaFormularioBusca(HttpSession session) {
		return "/buscarContato";
	}
	
	/**
	 * Lista os usuários da aplicação
	 * @param session Session do usuário da aplicação
	 * @param model Model da aplicação
	 * @return página TelaListarContatos.jsp | Home.jsp
	 * @throws IOException trata a exceção IOException caso aconteça
	 */
	@GetMapping(value="/listarContatos")
	public String processarListaContatos(HttpSession session, Model model) {
		List<Contato> lista = contatoService.listar();

		if (lista.size() > 0 ){
			model.addAttribute("contatos", lista);
		}else{
			model.addAttribute("mensagem", "Não há usuários cadastrados no sistema");
		}
		
		return "/listarContatos";
	}

	/**
	 * Faz a alteração dos dados de um usuário
	 * @param contato Contato da aplicação
	 * @param result BindingResult da aplicação para checar os erros
	 * @param session Session do usuário da aplicação
	 * @param redirect RedirectAttributes
	 * @return página TelaListarContatos.jsp | Home.jsp
	 * @throws ServletException trata a exceção ServletException caso aconteça
	 * @throws IOException trata a exceção IOException caso aconteça
	 */
	@RequestMapping(value="/alterarContato", method=RequestMethod.POST)
	public String processarAlterarContato(Contato contato,int id, HttpSession session, RedirectAttributes redirectAttribute) {
		String senhaOriginal;
		
		senhaOriginal = contato.getSenha();
		contato.setSenha(new GeradorSenha().criptografa(senhaOriginal));

		contatoService.alterar(contato,id);
		redirectAttribute.addFlashAttribute("mensagem", "Contato " + contato.getNome() + " alterado com sucesso!");
		
		return "redirect:/listarContatos";
	}

	/**
	 * Carrega o formulário Alterar Contato
	 * @param session Session do usuário da aplicação 
	 * @return página TelaAlterarContato.jsp | Home.jsp
	 */
	@RequestMapping("/alterarContato")
	public ModelAndView carregaFormularioAlterar(int id, HttpSession session) {
		Contato contato = contatoService.buscarPorId(id);
		
		ModelAndView mav = new ModelAndView("/alterarContato");
		mav.addObject("contato", contato);
		
		return (mav);
	}

	/**
	 * Carrega o formulário Inserir Contato
	 * @param session Session do usuário da aplicação
	 * @return página TelaInserirContato.jsp | Home.jsp
	 */
	@RequestMapping("/inserirContato")
	public String carregarFormularioInserir(HttpSession session, Model model) {
		Contato contato = new Contato();
		
		model.addAttribute("contato", contato);
		
		return "/inserirContato";
	}

	/**
	 * Insere usuário
	 * @param contato Dados do Contato
	 * @param result BindingResult que checa os erros de entrada da interface
	 * @param session Session do usuário da aplicação
	 * @param redirect RedirectAttributes mensagem de redirect
	 * @return página TelaPrincipal.jsp | Home.jsp
	 * @throws ServletException 
	 * @throws IOException
	 */
	@RequestMapping(value="/inserirContato", params={"save"})
	public String processarInserirContato(final Contato contato, HttpSession session, RedirectAttributes redirectAttribute,Model model){
		String senhaOriginal; 
			
		senhaOriginal = contato.getSenha();
		contato.setSenha(new GeradorSenha().criptografa(senhaOriginal));
		
		//TODO melhorar a busca de Role para evitar Detached
		Role regra = contatoService.buscaRole(2);
		List<Role> regras = new ArrayList<>();
		regras.add(regra);
		contato.setRoles(regras);
		
		List<Contato> contatos = contatoService.buscarPorCPF(contato.getCpf());
		
		if(contatos.size() > 0) {
			model.addAttribute("error", "CPF já está cadastrado no sistema!");
			return "inserirContato" ;
		}
		 
		contatoService.inserir(contato);
		redirectAttribute.addFlashAttribute("mensagem", "Contato inserido com sucesso!");
		
		return "redirect:/listarContatos";
	}
	
	/**
	 * Mostra detalhes do usuário selecionado
	 * @param id do usuário
	 * @param session do usuário logado na aplicação
	 * @return view TelaDetalhesContato.jsp | Home.jsp
	 */
	@RequestMapping("/detalharContato/{id}")
	public ModelAndView processarDetalhesContato(@PathVariable("id") int id, HttpSession session) {
		ModelAndView mav = new ModelAndView("/detalharContato");
		
		Contato contato = contatoService.buscarPorId(id);
		mav.addObject("contato", contato);
		
		return mav;
	}

	/**
	 * Remove o usuáro seleconado
	 * @param id do usuário selecionado
	 * @param session do usuário logado na aplicação
	 * @param redirectAttribute mensagem do tipo flash para evitar repetição de post
	 * @return view listaContatos | Home.jsp
	 */
	@RequestMapping("/removerContato/{id}")
	public String processarRemoverContato(@PathVariable("id") int id, HttpSession session, RedirectAttributes redirectAttribute) {
		Contato contato = contatoService.buscarPorId(id);
		
		contatoService.remover(contato);
		redirectAttribute.addFlashAttribute("mensagem", "Contato " + contato.getNome() + " removido com sucesso!");
		
		return "redirect:/listarContatos";
	}
	
	@RequestMapping("/meuperfil/{id}")
	public ModelAndView processarPerfilContato(@PathVariable("id") Integer id, HttpSession session) {
		ModelAndView mav = new ModelAndView("/perfilContato");
		//TODO conclui a funcionalidade de exibição do perfil do usuário
		return mav;
	}

}