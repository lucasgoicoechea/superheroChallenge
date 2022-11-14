package es.challenge.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import es.challenge.entities.SuperHero;

public interface SuperHeroDAORepository extends JpaRepository<SuperHero, Long> {

	List<SuperHero> findAllByNameContainingIgnoreCase(String criterion);
	
}
