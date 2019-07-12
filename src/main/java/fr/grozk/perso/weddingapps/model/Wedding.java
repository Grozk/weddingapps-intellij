package fr.grozk.perso.weddingapps.model;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import fr.grozk.perso.weddingapps.model.exception.ModelControlException;

public class Wedding {

	private final String partner1;
	private final String partner2;
	private final String number1;
	private final String number2;
	private final String weddingDate;
	private String idPublic;
	private final String mailCreator;

	@JsonCreator
	public Wedding(@JsonProperty("partner1") String partner1,@JsonProperty("partner2") String partner2, 
			@JsonProperty("number1") String number1,@JsonProperty("number2") String number2,
			@JsonProperty("weddingDate") String weddingDate, @JsonProperty("mailCreator") String mailCreator) {
		super();
		this.partner1 = partner1;
		this.partner2 = partner2;
		this.number1 = number1;
		this.number2 = number2;
		this.weddingDate = weddingDate;
		this.mailCreator = mailCreator;
	}

	public void control() throws ModelControlException {

		if (StringUtils.isEmpty(this.partner1) || StringUtils.isEmpty(this.partner2)) {
			throw new ModelControlException("PARTNER", "", "not null");
		}
		if (StringUtils.isEmpty(this.number1) || StringUtils.isEmpty(this.number2)) {
			throw new ModelControlException("NUMBER", "", "not null");
		}
		if (weddingDate == null) {
			throw new ModelControlException("WEDDING DATE", "", "not null");
		}
	}
	
	
	public String getPartner1() {
		return partner1;
	}
	
	public String getPartner2() {
		return partner2;
	}

	public String getNumber1() {
		return number1;
	}

	public String getNumber2() {
		return number2;
	}

	public String getWeddingDate() {
		return weddingDate;
	}

	public String getIdPublic() {
		return idPublic;
	}

	public void setIdPublic(String idPublic) {
		this.idPublic = idPublic;
	}

	public String getMailCreator() {
		return mailCreator;
	}

	@Override
	public String toString() {
		return "Wedding [partner1=" + partner1 + ", partner2=" + partner2 + ", number1=" + number1 + ", number2="
				+ number2 + ", weddingDate=" + weddingDate + ", idPublic=" + idPublic + ", mailCreator=" + mailCreator
				+ "]";
	}
}
