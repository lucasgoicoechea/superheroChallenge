package es.challenge.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import es.challenge.dtos.SuperHeroDTO;
import es.challenge.entities.SuperHero;
import es.challenge.parsersObjects.ParserSuperHero;
import es.challenge.repository.SuperHeroDAORepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SuperHeroRestServiceImpl implements SuperHeroRestService {

	@Autowired
	SuperHeroDAORepository superHeroDAORepository;

	@Autowired
	ParserSuperHero parserSuperHero;

	@PersistenceContext
	private EntityManager em;

	@Override
	@Cacheable("superheros")
	public List<SuperHero> findAll() {
		return superHeroDAORepository.findAll();
	}

	@Override
	public List<SuperHeroDTO> find(String criterio) {
		List<SuperHero> superheros = superHeroDAORepository.findAllByNameContainingIgnoreCase(criterio.toUpperCase());
		if (superheros.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empty list SuperHeros");
		}
		return parserSuperHero.toEntities(superheros);
	}

	@Override
	public SuperHeroDTO findById(Long id) {
		Optional<SuperHero> opt = superHeroDAORepository.findById(id);
		if (opt.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empty SuperHeros");
		}
		return parserSuperHero.toEntity(opt.get());
	}

	@Override
	public void update(SuperHeroDTO superHero) {
		SuperHero superheroPeristed = superHeroDAORepository.findById(superHero.getId()).orElse(null);
		if (superheroPeristed == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empty hero");
		}
		superheroPeristed.setName(superHero.getName());
		superHeroDAORepository.save(superheroPeristed);
	}

	@Override
	@CacheEvict("superheros")
	public void delete(Long id) {
		if (!superHeroDAORepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empty hero");
		}
		superHeroDAORepository.deleteById(id);
	}

}
