package br.edu.ufabc.MAPA.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ufabc.MAPA.Model.Entidade;
import br.edu.ufabc.MAPA.Service.EntidadeService;

@Controller
public class MainController{
	
	@Autowired
	private EntidadeService entidadeService;

    @RequestMapping(value="/")
    public String home(){
        return "home";
    }

	@RequestMapping("/entidade")
	public String entidade(Model model, @RequestParam("id") int id){
		Entidade entidade=entidadeService.findEntidadeById(id);
		if(entidade!=null)
			model.addAttribute("entidade", entidade);
		else
			model.addAttribute("entidade", new Entidade());

		return "entidade";
	}

	@RequestMapping(value="/searchResults")
	public String search(Model model, @RequestParam("q") String q){
		return "searchResults";
	}

	@RequestMapping(value="/login")
	public String login(Model model, String error, String logout){
		if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "redirect:/";
	}
    
	@RequestMapping(value = "/registration")
	public String registration(@Valid Entidade entidade, BindingResult bindingResult, Model model){
		//Entidade ent=entidadeService.findByEmail(entidade.getEmail());
		//if(userExists!=null){
		//	bindingResult
		//			.rejectValue("email", "error.user",
		//					"There is already a user registered with the email provided");
		//}
		return "redirect:/";
	}

}

