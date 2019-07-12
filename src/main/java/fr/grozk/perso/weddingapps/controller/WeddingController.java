package fr.grozk.perso.weddingapps.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.grozk.perso.weddingapps.model.Wedding;
import fr.grozk.perso.weddingapps.model.exception.ModelControlException;
import fr.grozk.perso.weddingapps.service.WeddingService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/wedding", produces = "application/json")
public class WeddingController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WeddingService weddingService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Wedding> createMessage(@RequestBody Wedding wedding) {

		try { 
			wedding.control();
			
			// Manage wedding
			weddingService.manageWedding(wedding);

		} catch (ModelControlException mce){
			LOGGER.error("Error while processing wedding:"+wedding.toString(),mce);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			LOGGER.error("Error while processing wedding:"+wedding.toString(),e);
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		return new ResponseEntity<>(wedding, HttpStatus.CREATED);
	}

}
