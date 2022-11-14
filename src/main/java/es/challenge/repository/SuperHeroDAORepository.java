package es.challenge.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.challenge.entities.SuperHero;

@Repository
public interface SuperHeroDAORepository extends JpaRepository<SuperHero, Long> {

	List<SuperHero> findAllByNameContainingIgnoreCase(String criterion);
	
}
