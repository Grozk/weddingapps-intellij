package fr.grozk.perso.weddingapps.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import fr.grozk.perso.weddingapps.entity.MessageEntity;
import fr.grozk.perso.weddingapps.entity.WeddingEntity;
import fr.grozk.perso.weddingapps.entity.repository.MessageRepository;
import fr.grozk.perso.weddingapps.entity.repository.WeddingRepository;
import fr.grozk.perso.weddingapps.model.Message;

@Service
public class MessageService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	WeddingRepository weddingRepository;
	

	@Value("${data.path}")
	private String dataPath;
	
	public void manageMessage(Message message) {
		
		// get technical ID
		WeddingEntity weddingEntity = weddingRepository.getWeddingFromToken(message.getIdPublic());
		message.setWeddingId(weddingEntity.getId());
		
		// save image and set the link inside message object
		if (!StringUtils.isEmpty(message.getImage())) {
			message.setImage(imageSaving(message.getImage(), String.valueOf(message.getWeddingId()), message.getNumberSender()));
		} else {
			message.setImage("");
		}
		
		// ajout du message en BDD
		MessageEntity messageEntity = new MessageEntity(message);
		messageRepository.save(messageEntity);
		
	}
	
	/**
	 * image recording

	 * @param encodedImage
	 * @param weddingID
	 * @param numberSender
	 * @return path of the recorded image
	 */
	private String imageSaving(String encodedImage, String weddingID, String numberSender) {
		// TODO controle de cohérence et de sécurité du fichier a dev
		byte[] decodedImg = Base64.getDecoder().decode(encodedImage.split(",")[1].getBytes(StandardCharsets.UTF_8));
		
		// TODO sortir dans un fichier de configuration (ou BDD)
		String pathRepertoryApp = dataPath+weddingID;
		String imageName = numberSender+"_"+simpleDateFormat.format(new Date())+".jpg";

		Path destinationFile = Paths.get(pathRepertoryApp, imageName);
		try {
			Files.write(destinationFile, decodedImg);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(),e);
		}
		
		return destinationFile.toString();
	}
}
