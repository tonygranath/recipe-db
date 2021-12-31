package se.lexicon.tonygranath.recipedb.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
	private static final Recipe RECIPE = new Recipe("testRecipe", null, null, null);
	private static final RecipeCategory CAT1 = new RecipeCategory("Kött");
	private static final RecipeCategory CAT2 = new RecipeCategory("Test");
	private static final RecipeCategory CAT3 = new RecipeCategory("Fisk");
	private static final RecipeCategory CAT4 = new RecipeCategory("Växtbaserat");
	private static final Ingredient INGREDIENT1 = new Ingredient("Lök");
	private static final Ingredient INGREDIENT2 = new Ingredient("Hästkött");
	private static final Ingredient INGREDIENT3 = new Ingredient("Vatten");
	private static final Ingredient INGREDIENT4 = new Ingredient("Tomat");

	private static final RecipeIngredient RECIPE_INGREDIENT1 = new RecipeIngredient(
			INGREDIENT1, 1.0, Measurement.g, RECIPE
	);
	private static final RecipeIngredient RECIPE_INGREDIENT2 = new RecipeIngredient(
			INGREDIENT1, 250.0, Measurement.kg, RECIPE
	);
	private static final RecipeIngredient RECIPE_INGREDIENT3 = new RecipeIngredient(
			INGREDIENT2, 100.0, Measurement.hg, null
	);

	@BeforeEach
	void setUp() {
		List<RecipeIngredient> ingredients = new ArrayList<>();
		ingredients.add(RECIPE_INGREDIENT1);
		ingredients.add(RECIPE_INGREDIENT2);
		RECIPE.setIngredients(ingredients);
		Set<RecipeCategory> categories = new HashSet<>();
		categories.add(CAT1);
		categories.add(CAT2);
		categories.add(CAT3);
		RECIPE.setCategories(categories);
	}

	@Test
	void setIngredients() {
		assertTrue(RECIPE.getIngredients().contains(RECIPE_INGREDIENT1));
		assertTrue(RECIPE.getIngredients().contains(RECIPE_INGREDIENT2));
		assertEquals(2, RECIPE.getIngredients().size());
	}

	@Test
	void addIngredient() {
		assertEquals(2, RECIPE.getIngredients().size());
		RECIPE.addIngredient(RECIPE_INGREDIENT3);
		assertEquals(3, RECIPE.getIngredients().size());
		assertTrue(RECIPE.getIngredients().contains(RECIPE_INGREDIENT3));
		assertThrows(IllegalArgumentException.class, () -> RECIPE.addIngredient(null));
	}

	@Test
	void removeIngredient() {
		assertEquals(2, RECIPE.getIngredients().size());
		RECIPE.removeIngredient(RECIPE_INGREDIENT1);
		assertFalse(RECIPE.getIngredients().contains(RECIPE_INGREDIENT1));
		assertEquals(1, RECIPE.getIngredients().size());
		assertThrows(IllegalArgumentException.class, () -> RECIPE.removeIngredient(null));
	}

	@Test
	void addCategory() {
		assertEquals(3, RECIPE.getCategories().size());
		RECIPE.addCategory(CAT4);
		assertEquals(4, RECIPE.getCategories().size());
		assertThrows(IllegalArgumentException.class, () -> RECIPE.addCategory(null));
	}

	@Test
	void removeCategory() {
		assertEquals(3, RECIPE.getCategories().size());
		RECIPE.removeCategory(CAT1);
		RECIPE.removeCategory(CAT3);
		assertEquals(1, RECIPE.getCategories().size());
		assertFalse(RECIPE.getCategories().contains(CAT1));
		assertFalse(RECIPE.getCategories().contains(CAT3));
		assertThrows(IllegalArgumentException.class, () -> RECIPE.removeCategory(null));
	}

	@Test
	void setCategories() {
		assertEquals(3, RECIPE.getCategories().size());
		assertTrue(RECIPE.getCategories().contains(CAT1));
		assertTrue(RECIPE.getCategories().contains(CAT2));
		assertTrue(RECIPE.getCategories().contains(CAT3));
	}
}