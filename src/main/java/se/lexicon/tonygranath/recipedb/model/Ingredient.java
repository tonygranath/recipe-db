package se.lexicon.tonygranath.recipedb.model;

import org.hibernate.annotations.GenericGenerator;
import se.lexicon.tonygranath.recipedb.model.constants.EntityConstants;

import javax.persistence.*;

@Entity
public class Ingredient {
	@Id
	@GeneratedValue(generator = EntityConstants.GENERATOR)
	@GenericGenerator(
			name = EntityConstants.GENERATOR_NAME,
			strategy = EntityConstants.GENERATOR_STRATEGY
	)
	@Column(updatable = false)
	private String id;
	@Column(unique = true, nullable = false)
	private String name;

	public Ingredient(String name) {
		this.name = name;
	}

	protected Ingredient() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
