package es.challenge.services;

import java.util.List;

import es.challenge.dtos.SuperHeroDTO;
import es.challenge.entities.SuperHero;

public interface SuperHeroRestService {

    List<SuperHero> findAll();

    SuperHeroDTO findById(Long id);

    List<SuperHeroDTO> find(String criterio);

    void update(SuperHeroDTO superHero);

    void delete(Long id);
}
