package es.challenge.services;

import java.util.List;

import e.challenge.dtos.SuperHeroDTO;

public interface SuperHeroRestService {

    List<SuperHeroDTO> findAll();

    SuperHeroDTO findById(Long id);

    List<SuperHeroDTO> find(String criterio);

    void update(SuperHeroDTO superHero);

    void delete(Long id);
}
