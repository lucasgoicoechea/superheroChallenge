package es.challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperHeroDTO {

    private Long id;
    private String name;
    
    public Long getId() {
		return id;
	}
    
    public String getName() {
		return name;
	}
    
    public void setName(String name) {
		this.name = name;
	}
}
