package es.challenge.controller;

import es.challenge.aspectFunctions.Counter;
import es.challenge.dtos.SuperHeroDTO;
import es.challenge.services.SuperHeroRestService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SuperHeroController {

	@Autowired
    private SuperHeroRestService service;

    @GetMapping
    @Counter
    public ResponseEntity<List<SuperHeroDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
    	SuperHeroDTO superHeroe = service.findById(id);
        return ResponseEntity.ok(superHeroe);
    }

    @GetMapping("/find/{criterio}")
    public ResponseEntity<?> buscar(@PathVariable String criterio) {
        List<SuperHeroDTO> superHeroes = service.find(criterio);
        return ResponseEntity.ok(superHeroes);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody SuperHeroDTO superHeroe) {
        service.update(superHeroe);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
