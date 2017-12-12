package br.edu.ufabc.MAPA.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ufabc.MAPA.Model.Entidade;
import br.edu.ufabc.MAPA.Model.Tag;
import br.edu.ufabc.MAPA.Service.EntidadeService;
import br.edu.ufabc.MAPA.Service.SecurityService;

@Controller
public class MainController{
	
	@Autowired
	private EntidadeService entidadeService;

	@Autowired
	private SecurityService securityService;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index(Model model){
		List<Entidade> list=entidadeService.findAll();

		model.addAttribute("entidades", list);

		model.addAttribute("user",  securityService.findLoggedInUsername() );

        return "index";
    }

	@RequestMapping(value="/entidade", method=RequestMethod.GET)
	public String entidade(Model model, @RequestParam("id") int id){
		Entidade entidade=entidadeService.findEntidadeById(id);
		model.addAttribute("entidadeDetails", entidade);

		model.addAttribute("user",  securityService.findLoggedInUsername() );

		return "entidade";
	}

	@RequestMapping(value="/tag", method=RequestMethod.GET)
	public String tag(Model model, @RequestParam("id") int id){
		Tag t=entidadeService.findTagById(id);
		model.addAttribute("tag", t.getNome());

		List<Entidade> list=entidadeService.findEntidadeByTag(t);

		model.addAttribute("searchResults", list);

		model.addAttribute("user",  securityService.findLoggedInUsername() );

		return "tag";
	}

	@RequestMapping(value="/searchResults", method=RequestMethod.POST)
	public String search(Model model, @RequestParam("q") String q){
		List<Entidade> lista=entidadeService.findEntidadeByNome(q);

		model.addAttribute("searchResults", lista);

		model.addAttribute("user",  securityService.findLoggedInUsername() );

		return "searchResults";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
	}
    
	@RequestMapping(value="/registrar", method=RequestMethod.GET)
	public String registration(){
		return "registrar";
	}

	@RequestMapping(value="/registrar", method=RequestMethod.POST)
	public String registration(Model model, @RequestParam String email, @RequestParam String nome, @RequestParam String senha, @RequestParam String senha2){
		if(!senha.equals(senha2)){
			model.addAttribute("error", "senha diferentes");
			return "registrar";
		}

		Entidade ent=entidadeService.findEntidadeByEmail(email);
		if(ent!=null){
			model.addAttribute("error", "There is already a user registered with the email provided");
			return "registrar";
		}

		Entidade entidade=new Entidade();
		entidade.setEmail(email);
		entidade.setNome(nome);
		entidade.setSenha(senha);
		entidadeService.newEntidade(entidade);

		securityService.autologin(entidade.getEmail(), senha);

		return "redirect:/";
	}

	@RequestMapping(value="/settings", method = RequestMethod.GET)
	public String settings(Model model){
		String nome=securityService.findLoggedInUsername();
		model.addAttribute("user", nome);

		Entidade entidade=entidadeService.findEntidadeByEmail(nome);
		model.addAttribute("nome", entidade.getNome());
		model.addAttribute("resumo", entidade.getResumo());
		model.addAttribute("descricao", entidade.getDescricao());

		Set<Tag> tags=entidade.getTags();
		String tag="";
		for(Tag t : tags)
			tag+=t.getNome()+" ";

		model.addAttribute("tags", tag);

		return "settings";
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(Model model, @RequestParam String nome, @RequestParam String resumo, @RequestParam String tags, @RequestParam String descricao){
		Entidade entidade=entidadeService.findEntidadeByEmail(securityService.findLoggedInUsername());
		entidade.setNome(nome);
		entidade.setResumo(resumo);
		entidade.setDescricao(descricao);
		Set<Tag> set=new HashSet<>();
		Scanner scan=new Scanner(tags);
		while(scan.hasNext()){
			String elem=scan.next();
			Tag t=entidadeService.findTagByNome(elem);
			if(t==null){
				t=new Tag();
				t.setNome(elem);
				entidadeService.saveTag(t);
			}

			set.add(t);
		}
		entidade.setTags(set);

		entidadeService.saveEntidade(entidade);

		return "redirect:/settings";
	}

	@RequestMapping(value="/image", method = RequestMethod.GET)
	public String image(Model model){
		model.addAttribute("user",  securityService.findLoggedInUsername() );

		return "image";
	}

	@RequestMapping(value="/uploadImage", method = RequestMethod.POST)
	public String upload(Model model, @RequestParam MultipartFile file) throws Exception{
		if(file.isEmpty()){
			model.addAttribute("Error", "Imagem");
			return "redirect:/image";
		}
		Entidade entidade=entidadeService.findEntidadeByEmail(securityService.findLoggedInUsername());
		entidade.setImagem(file.getBytes());
		entidadeService.saveEntidade(entidade);
		model.addAttribute("message", "Imagem alterada com sucesso");

		return "redirect:/image";
	}

	@RequestMapping(value="/conta", method = RequestMethod.GET)
	public String conta(Model model){
		model.addAttribute("user",  securityService.findLoggedInUsername() );

		return "conta";
	}

	@RequestMapping(value="/updateSenha", method=RequestMethod.POST)
	public String updateSenha(Model model, @RequestParam String senha1, @RequestParam String senha2){
		if(!senha1.equals(senha2)){
			model.addAttribute("error", "Senhas Diferentes");
			return "redirect:/conta";
		}
		Entidade entidade=entidadeService.findEntidadeByEmail(securityService.findLoggedInUsername());
		entidade.setSenha(senha1);
		entidadeService.newEntidade(entidade);

		model.addAttribute("message", "Senha Alterada com Sucesso");

		return "redirect:/conta";
	}

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(Model model){
		Entidade entidade=entidadeService.findEntidadeByEmail(securityService.findLoggedInUsername());
		entidadeService.delete(entidade);

		return "redirect:/logout";
	}
	
}

