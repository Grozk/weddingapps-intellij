package fr.grozk.perso.weddingapps.entity.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.grozk.perso.weddingapps.entity.WeddingEntity;

@Repository
@Transactional(readOnly = true)
public class WeddingRepositoryImpl implements WeddingCustomRepository {
	

    @PersistenceContext
    EntityManager entityManager;
	
	@Override
	public Object isTokenAlreadyUsed(String token) {
		
		Query query = entityManager.createNativeQuery("SELECT EXISTS(SELECT * FROM WEDDING we WHERE we.id_public = '"+token+"');");
                
        return query.getSingleResult();
	}

	@Override
	public WeddingEntity getWeddingFromToken(String token) {
		Query query = entityManager.createNativeQuery("SELECT * FROM WEDDING we WHERE we.id_public = '"+token+"';", WeddingEntity.class);
        
        return (WeddingEntity) query.getSingleResult();
	}

}
