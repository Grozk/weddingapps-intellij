package fr.grozk.perso.weddingapps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "*")
@Controller
public class IndexController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/index", produces = "application/html")
	public String welcome() {
		return "index";
	}
	
	@RequestMapping(value="/index/wedding", method = RequestMethod.GET)
	public String joinWedding() {
		return "wedding";
	}
	
	@RequestMapping(value="/index/message", method = RequestMethod.GET)
	public String createMessage() {
		return "message";
	}
	
}
