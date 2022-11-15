package es.challenge.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.challenge.dtos.SuperHeroDTO;
import es.challenge.entities.SuperHero;


@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:script.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class SuperHeroRestServiceImplTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SuperHeroRestService service;

    @Test
    @WithMockUser(username = "user", password = "user")
	void getAll_shouldReturnSuperheroeList() throws Exception {
	    mvc.perform(get("/superhero/api"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$").isArray());
	}

	@Test
	@WithMockUser(username = "user", password = "user")
	void getById_shouldReturnSuperheroe() throws Exception {
	    Long id = 1L;
	
	    mvc.perform(get("/superhero/api/{id}", id))
	            .andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "user", password = "user")
	void getById_shouldReturnSuperheroeNotFound() throws Exception {
	    Long id = 55L;
	
	    mvc.perform(get("/superhero/api/{id}", id))
	            .andExpect(status().isNotFound());
	}
	
	@Test
	@WithMockUser(username = "user", password = "user")
	void buscar_shouldReturnSuperheroe() throws Exception {
	    String criterio = "man";
	
	    mvc.perform(get("/superhero/api/find/{criterio}", criterio))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	@WithMockUser(username = "user", password = "user")
	void buscar_shouldReturnNotFound() throws Exception {
	    String criterio = "manolitos";
	
	    mvc.perform(get("/superhero/find/{criterio}", criterio))
	            .andExpect(status().isNotFound())
	            .andExpect(jsonPath("$.status", is(404)));
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	void update_shouldReturnNoContentandVerifyValue() throws Exception {
	    SuperHeroDTO superheroe = new SuperHeroDTO(1L, "modificado");
	
	    mvc.perform(put("/superhero")
	            .contentType(MediaType.APPLICATION_JSON_VALUE)
	            .content(objectMapper.writeValueAsString(superheroe)))
	            .andExpect(status().isNoContent());
	
	    SuperHeroDTO modificado = service.findById(1L);
	    assertThat(modificado.getName()).isEqualTo(" modificado");
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	void update_shouldReturnNotFound() throws Exception {
	    SuperHero sp = new SuperHero(53L, "Seguro que no existe");

	    mvc.perform(put("/superhero")
	            .contentType(MediaType.APPLICATION_JSON_VALUE)
	            .content(objectMapper.writeValueAsString(sp)))
	            .andExpect(status().isNotFound())
	            .andExpect(jsonPath("$.status", is(404)));
	}

	@Test
	@WithMockUser(username = "user", password = "user")
	void delete_shouldReturnNoContentAndVerifyDeletion() throws Exception {
	    Long id = 1L;
	
	    mvc.perform(delete("/superhero/api/{id}", id))
	            .andExpect(status().isNoContent());
	
	    assertThrows(ResponseStatusException.class, () -> service.findById(1L));
	}
	
	@Test
	@WithMockUser(username = "user", password = "user")
	void delete_shouldReturnNotFound() throws Exception {
    Long id = 555L;

    mvc.perform(delete("/superhero/api/{id}", id))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status", is(404)));
	    }
	}


