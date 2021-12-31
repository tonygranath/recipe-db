package se.lexicon.tonygranath.recipedb.model;

import org.hibernate.annotations.GenericGenerator;
import se.lexicon.tonygranath.recipedb.model.constants.EntityConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RecipeInstruction {
	@Id
	@GeneratedValue(generator = EntityConstants.GENERATOR)
	@GenericGenerator(
			name = EntityConstants.GENERATOR_NAME,
			strategy = EntityConstants.GENERATOR_STRATEGY
	)
	@Column(updatable = false)
	private String id;
	private String instruction;

	public RecipeInstruction(String instruction) {
		setInstruction(instruction);
	}

	protected RecipeInstruction() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
}
