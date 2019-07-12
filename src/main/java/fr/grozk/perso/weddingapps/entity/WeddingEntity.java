package fr.grozk.perso.weddingapps.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.grozk.perso.weddingapps.model.Wedding;

@Entity
@Table(name = "WEDDING")
public class WeddingEntity {
	
	@Transient
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Transient
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "partner1")
	private String partner1;

	@Column(name = "partner2")
	private String partner2;

	@Column(name = "number1")
	private String number1;

	@Column(name = "number2")
	private String number2;

	@Column(name = "wedding_date")
	private Date wedding_date;
	
	@Column(name = "id_public")
	private String id_public;

	@Column(name = "mail_Creator")
	private String mail_Creator;
	
	public WeddingEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WeddingEntity(Long id, String partner1, String partner2, String number1, String number2,
			Date wedding_date, String id_public, String mailCreator) {
		super();
		this.id = id;
		this.partner1 = partner1;
		this.partner2 = partner2;
		this.number1 = number1;
		this.number2 = number2;
		this.wedding_date = wedding_date;
		this.id_public = id_public;
		this.mail_Creator = mailCreator;
	}
	
	public WeddingEntity(Wedding wedding) {
		this.partner1 = wedding.getPartner1();
		this.partner2 = wedding.getPartner2();
		this.number1 = wedding.getNumber1();
		this.number2 = wedding.getNumber2();
		this.id_public = wedding.getIdPublic();
		this.mail_Creator = wedding.getMailCreator();
		
		try {
			this.wedding_date = simpleDateFormat.parse(wedding.getWeddingDate());
		} catch (ParseException e) {
			LOGGER.error("Parse error on wedding date : "+wedding.getWeddingDate()+" ."+e.getMessage(),e);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPartner1() {
		return partner1;
	}

	public void setPartner1(String partner1) {
		this.partner1 = partner1;
	}

	public String getPartner2() {
		return partner2;
	}

	public void setPartner2(String partner2) {
		this.partner2 = partner2;
	}

	public String getNumber1() {
		return number1;
	}

	public void setNumber1(String number1) {
		this.number1 = number1;
	}

	public String getNumber2() {
		return number2;
	}

	public void setNumber2(String number2) {
		this.number2 = number2;
	}

	public Date getWedding_date() {
		return wedding_date;
	}

	public void setWedding_date(Date wedding_date) {
		this.wedding_date = wedding_date;
	}

	public String getId_public() {
		return id_public;
	}

	public void setId_public(String id_public) {
		this.id_public = id_public;
	}

	public String getMailCreator() {
		return mail_Creator;
	}

	public void setMailCreator(String mailCreator) {
		this.mail_Creator = mailCreator;
	}

}
