package fr.grozk.perso.weddingapps.model;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import fr.grozk.perso.weddingapps.model.exception.ModelControlException;

public class Message {
	
	
	private Long weddingId;
	private final String message;
	private String image;
	private final String numberSender;
	private final String nameSender;
	private final String idPublic;
		
	@JsonCreator
	public Message(@JsonProperty("message") String message,
			@JsonProperty("image") String image, @JsonProperty("numberSender") String numberSender,
			@JsonProperty("nameSender") String nameSender, @JsonProperty("idPublic") String idPublic) {
		super();
		this.message = message;
		this.numberSender = numberSender;
		this.nameSender = nameSender;
		this.idPublic = idPublic;
		this.image = image;
	}
	
	public void control() throws ModelControlException {
		if (StringUtils.isEmpty(this.idPublic)) {
			throw new ModelControlException("ID PUBLIC", "", "not null");
		}
		if (StringUtils.isEmpty(this.message)) {
			throw new ModelControlException("MESSAGE", "", "not null");
		}
		if (StringUtils.isEmpty(this.nameSender)) {
			throw new ModelControlException("NAME SENDER", "", "not null");
		}
	}

	public Long getWeddingId() {
		return weddingId;
	}
	
	public void setWeddingId(Long weddingID) {
		this.weddingId = weddingID;
	}

	public String getMessage() {
		return message;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNumberSender() {
		return numberSender;
	}

	public String getNameSender() {
		return nameSender;
	}

	public String getIdPublic() {
		return idPublic;
	}

	@Override
	public String toString() {
		return "Message [weddingId=" + weddingId + ", message=" + message + ", image=" + image + ", numberSender="
				+ numberSender + ", nameSender=" + nameSender + ", idPublic=" + idPublic + "]";
	}
}
