package fr.grozk.perso.weddingapps.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.grozk.perso.weddingapps.entity.WeddingEntity;

@Repository
public interface WeddingRepository extends JpaRepository<WeddingEntity, Long>, WeddingCustomRepository{
	
}
