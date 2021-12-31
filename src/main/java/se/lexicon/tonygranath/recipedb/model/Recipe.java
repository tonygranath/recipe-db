package se.lexicon.tonygranath.recipedb.model;

import org.hibernate.annotations.*;
import se.lexicon.tonygranath.recipedb.database.RecipeIngredientRepository;
import se.lexicon.tonygranath.recipedb.model.constants.EntityConstants;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.*;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(generator = EntityConstants.GENERATOR)
	@GenericGenerator(name = EntityConstants.GENERATOR_NAME, strategy = EntityConstants.GENERATOR_STRATEGY)
	@Column(updatable = false)
	private String id;
	private String name;
	@OneToMany(
			//cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE},
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			orphanRemoval = true
	)
	private List<RecipeIngredient> ingredients = new ArrayList<>();
	@OneToOne(
			//cascade = {CascadeType.DETACH, CascadeType.REFRESH},
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, orphanRemoval = true
	)
	@JoinColumn(name = "fk_recipe_instruction_id")
	private RecipeInstruction instruction;
	@ManyToMany(
			//cascade = {CascadeType.DETACH, CascadeType.REFRESH},
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinTable(name = "recipe_categories",
	joinColumns = @JoinColumn(name = "fk_recipe_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "fk_category_id", referencedColumnName = "id"))
	private Set<RecipeCategory>	categories = new HashSet<>();

	public Recipe(String name, List<RecipeIngredient> ingredients, RecipeInstruction instruction, Set<RecipeCategory> categories) {
		this(name, ingredients, instruction);
		setCategories(categories);
	}

	public Recipe(String name, List<RecipeIngredient> ingredients, RecipeInstruction instruction) {
		this(name);
		setIngredients(ingredients);
		setInstruction(instruction);
	}

	public Recipe(String name) {
		setName(name);
	}

	protected Recipe() {}

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

	public Collection<RecipeIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<RecipeIngredient> ingredients) {
		if (ingredients == null) {
			ingredients = new ArrayList<>();
		}
		for(RecipeIngredient i : ingredients) {
			i.setRecipe(this);
			addIngredient(i);
		}
	}

	public void addIngredient(RecipeIngredient ingredient) {
		if (ingredient == null)
			throw new IllegalArgumentException("ingredient was null.");
		ingredients.add(ingredient);
	}

	public void removeIngredient(RecipeIngredient ingredient) {
		if (ingredient == null)
			throw new IllegalArgumentException("ingredient was null.");
		ingredients.remove(ingredient);
		ingredient.setRecipe(null);
	}

	public RecipeInstruction getInstruction() {
		return instruction;
	}

	public void setInstruction(RecipeInstruction instruction) {
		this.instruction = instruction;
	}

	public Set<RecipeCategory> getCategories() {
		for (RecipeCategory c : categories)
			System.out.println(c.getName());
		return categories;
	}

	public void setCategories(Set<RecipeCategory> categories) {
		if (categories == null) {
			categories = new HashSet<>();
		}
		this.categories = categories;
	}
}
