package fr.grozk.perso.weddingapps.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

@Service
public class FileUtilService {

	public void createDirectory(String pathToCreate) throws IOException {

		Path path = Paths.get(pathToCreate);
		Files.createDirectories(path);
		
	}
	
}
