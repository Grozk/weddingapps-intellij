package fr.grozk.perso.weddingapps.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.grozk.perso.weddingapps.model.Message;
import fr.grozk.perso.weddingapps.model.exception.ModelControlException;
import fr.grozk.perso.weddingapps.service.MessageService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/message", produces = "application/json")
public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Message> createMessage(@RequestBody Message message) {

		try { 
			message.control();
			
			messageService.manageMessage(message);
			
		} catch (ModelControlException mce){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	
	/********************************************************************/
	/******************EXEMPLE*******************************************/
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Message>> findAllMessages() {
		ArrayList<Message> listMsg = new ArrayList<>();
		listMsg.add(new Message("coucou", "lqksdjqslkdjqslkdj", "0664823274","Goul", "XHDGR"));
		return new ResponseEntity<>(listMsg, HttpStatus.OK);
	}
		
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Message> findMessageById(@PathVariable Long id) {
		Message me = new Message("coucou", "lqksdjqslkdjqslkdj", "0664823274","Goul", "XHDGR");
		if (true) {
			return new ResponseEntity<>(me, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
		Message me = new Message("coucou", "lqksdjqslkdjqslkdj", "0664823274","Goul", "JDHEU");

//		HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message message) {
		Message me = new Message(message.getMessage(), message.getImage(),
				message.getNumberSender(), message.getNameSender(), message.getIdPublic());

		if (true) {
			return findMessageById(id);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
