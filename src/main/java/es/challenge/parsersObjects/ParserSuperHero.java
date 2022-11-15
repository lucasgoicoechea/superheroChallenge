package es.challenge.parsersObjects;

import java.beans.JavaBean;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import es.challenge.dtos.SuperHeroDTO;
import es.challenge.entities.SuperHero;

/**
 * @author lgoicoechea
 * used for convert Data Object to DTO 
 */


@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface ParserSuperHero {

    SuperHeroDTO toEntity(SuperHero superHero);
    List<SuperHeroDTO> toEntities(List<SuperHero> superHeroList);
}
