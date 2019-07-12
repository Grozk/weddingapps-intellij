package fr.grozk.perso.weddingapps.entity.repository;

import fr.grozk.perso.weddingapps.entity.WeddingEntity;

public interface WeddingCustomRepository {

	public Object isTokenAlreadyUsed(String token);
	
	public WeddingEntity getWeddingFromToken(String token);

}
