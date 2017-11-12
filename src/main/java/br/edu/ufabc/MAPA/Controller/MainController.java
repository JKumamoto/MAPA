package br.edu.ufabc.MAPA.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
  
@Controller
public class MainController{
	
    @RequestMapping(value={"/", "/home"},method = RequestMethod.GET)
    public String home(){
        return "home";
    }

	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(){
		return "login";
	}
    
    @ResponseBody
    @RequestMapping(value="/greeting",method = RequestMethod.GET)
    public String Greeting(){
        return "Message From SpringBoot Service - Hello World!";
    }
}

