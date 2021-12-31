package se.lexicon.tonygranath.recipedb.model;

import org.hibernate.annotations.GenericGenerator;
import se.lexicon.tonygranath.recipedb.model.constants.EntityConstants;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class RecipeCategory {
	@Id
	@GeneratedValue(generator = EntityConstants.GENERATOR)
	@GenericGenerator(
			name = EntityConstants.GENERATOR_NAME,
			strategy = EntityConstants.GENERATOR_STRATEGY
	)
	@Column(updatable = false)
	private String id;
	private String name;
	@ManyToMany(
			cascade = {CascadeType.DETACH, CascadeType.REFRESH},
			fetch = FetchType.LAZY, mappedBy = "categories"
	)
	private Set<Recipe> recipes = new HashSet<>();

	public RecipeCategory(String name) {
		setName(name);
	}

	protected RecipeCategory() {}

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

	public Set<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(Set<Recipe> recipes) {
		if (recipes == null)
			recipes = new HashSet<>();
		for(Recipe r : recipes) {
			addRecipe(r);
		}
	}

	public void addRecipe(Recipe recipe) {
		if (recipe == null)
			throw new IllegalArgumentException("recipe was null.");
		recipes.add(recipe);
	}

	public void removeRecipe(Recipe recipe) {
		if (recipe == null)
			throw new IllegalArgumentException("recipe was null.");
		recipes.remove(recipe);
	}
}
