package es.challenge.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "superhero")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperHero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    public SuperHero(long l, String string) {
		this.id = l;
		this.name = string;
	}

	public void setName(String name) {
		this.name = name;
	}
}
