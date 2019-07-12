package fr.grozk.perso.weddingapps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.grozk.perso.weddingapps.model.Message;

@Entity
@Table(name = "MESSAGE")
public class MessageEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "wedding_id")
	private Long wedding_id;
	
	@Column(name = "message")
	private String message;

	@Column(name = "image")
	private String image;

	@Column(name = "number_sender")
	private String number_sender;

	@Column(name = "name_sender")
	private String name_sender;

	@Column(name = "message_sent")
	private boolean message_sent;

	public MessageEntity(Long id, Long wedding_id, String message, String image, String number_sender,
			String name_sender, boolean message_sent) {
		super();
		this.id = id;
		this.wedding_id = wedding_id;
		this.message = message;
		this.image = image;
		this.number_sender = number_sender;
		this.name_sender = name_sender;
		this.message_sent = message_sent;
	}
	
	public MessageEntity(Message message) {
		this.wedding_id = message.getWeddingId();
		this.message = message.getMessage();
		this.image = message.getImage();
		this.number_sender = message.getNumberSender();
		this.name_sender = message.getNameSender();
		this.message_sent = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWedding_id() {
		return wedding_id;
	}

	public void setWedding_id(Long wedding_id) {
		this.wedding_id = wedding_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNumber_sender() {
		return number_sender;
	}

	public void setNumber_sender(String number_sender) {
		this.number_sender = number_sender;
	}

	public String getName_sender() {
		return name_sender;
	}

	public void setName_sender(String name_sender) {
		this.name_sender = name_sender;
	}

	public boolean isMessage_sent() {
		return message_sent;
	}

	public void setMessage_sent(boolean message_sent) {
		this.message_sent = message_sent;
	}
	
}
