package br.edu.ufabc.MAPA.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ufabc.MAPA.Model.Entidade;
import br.edu.ufabc.MAPA.Service.EntidadeService;
import br.edu.ufabc.MAPA.Service.SecurityService;

@Controller
public class MainController{
	
	@Autowired
	private EntidadeService entidadeService;

	@Autowired
	private SecurityService securityService;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home(Model model){
        return "home";
    }

	@RequestMapping(value="/entidade", method=RequestMethod.GET)
	public String entidade(Model model, @RequestParam("id") int id){
		Entidade entidade=entidadeService.findEntidadeById(id);
		model.addAttribute("entidadeDetails", entidade);

		return "entidade";
	}

	@RequestMapping(value="/searchResults", method=RequestMethod.POST)
	public String search(Model model, @RequestParam("q") String q){
		List<Entidade> lista=entidadeService.findEntidadeByNome(q);
		model.addAttribute("searchResults", lista);
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
		entidadeService.saveEntidade(entidade);

		securityService.autologin(entidade.getEmail(), entidade.getSenha());

		return "redirect:/admin";
	}

	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public String admin(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Entidade entidade=entidadeService.findEntidadeByEmail(auth.getName());
		model.addAttribute("userName", "Welcome " + entidade.getNome() + " (" + entidade.getEmail() + ")");
		model.addAttribute("adminMessage","Content Available Only for Users with Admin Role");

		return "admin";
	}

}

