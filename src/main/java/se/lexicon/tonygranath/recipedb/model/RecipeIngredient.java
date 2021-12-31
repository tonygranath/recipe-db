package se.lexicon.tonygranath.recipedb.model;

import org.hibernate.annotations.GenericGenerator;
import se.lexicon.tonygranath.recipedb.model.constants.EntityConstants;

import javax.persistence.*;

@Entity
public class RecipeIngredient {
	@Id
	@GeneratedValue(generator = EntityConstants.GENERATOR)
	@GenericGenerator(
			name = EntityConstants.GENERATOR_NAME,
			strategy = EntityConstants.GENERATOR_STRATEGY
	)
	@Column(updatable = false)
	private String id;
	private double amount;
	private Measurement unit;
	@ManyToOne(
			cascade = {CascadeType.DETACH, CascadeType.REFRESH},
			fetch = FetchType.EAGER
	)
	private Ingredient ingredient;
	@ManyToOne(
			cascade = {CascadeType.DETACH, CascadeType.REFRESH},
			fetch = FetchType.LAZY
	)
	@JoinColumn(name = "fk_recipe_id")
	private Recipe recipe;

	public RecipeIngredient(Ingredient ingredient, double amount, Measurement unit, Recipe recipe) {
		this(ingredient, amount, unit);
		setRecipe(recipe);
		if (recipe != null)
			recipe.addIngredient(this);
	}

	public RecipeIngredient(Ingredient ingredient, double amount, Measurement unit) {
		setIngredient(ingredient);
		setAmount(amount);
		setUnit(unit);
	}

	protected RecipeIngredient() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Measurement getUnit() {
		return unit;
	}

	public void setUnit(Measurement unit) {
		this.unit = unit;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
}
