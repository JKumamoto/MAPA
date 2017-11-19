package br.edu.ufabc.MAPA.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
  
import br.edu.ufabc.MAPA.Model.Entidade;
import br.edu.ufabc.MAPA.Service.EntidadeService;

@Controller
public class MainController{
	
	@Autowired
	private EntidadeService entidadeService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String home(){
        return "home";
    }

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Entidade entidade=entidadeService.findByEmail(auth.getName());
		//modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		//modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		//modelAndView.setViewName("admin/home");
		return modelAndView;
	}
    
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid Entidade entidade, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		Entidade ent=entidadeService.findByEmail(entidade.getEmail());
		//if(userExists!=null){
		//	bindingResult
		//			.rejectValue("email", "error.user",
		//					"There is already a user registered with the email provided");
		//}
		if(bindingResult.hasErrors()){
			modelAndView.setViewName("registration");
		}else{
			//userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("entidade", new Entidade());
			modelAndView.setViewName("registration");
		}
		return modelAndView;
	}

	@ResponseBody
    @RequestMapping(value="/greeting",method = RequestMethod.GET)
    public String Greeting(){
        return "Message From SpringBoot Service - Hello World!";
    }
}

