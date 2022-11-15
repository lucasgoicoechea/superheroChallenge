package es.challenge.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.challenge.entities.SuperHero;

@Repository
public interface SuperHeroDAORepository extends CrudRepository<SuperHero, Long> {

	List<SuperHero> findAllByNameContainingIgnoreCase(String criterion);
	
}
