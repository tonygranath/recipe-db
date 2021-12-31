package se.lexicon.tonygranath.recipedb.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCategoryTest {
	private static final RecipeCategory CAT1 = new RecipeCategory("testCategory1");
	private static final RecipeCategory CAT2 = new RecipeCategory("testCategory2");
	private Set<RecipeCategory> categories = new HashSet<>();
	private static final Recipe RECIPE = new Recipe("testRecipe", null, null, null);
	private static final Recipe RECIPE2 = new Recipe("testRecipe2", null, null, null);
	private static final Recipe RECIPE3 = new Recipe("testRecipe3", null, null, null);

	@BeforeEach
	void setUp() {
		categories.add(CAT1);
		categories.add(CAT2);
		CAT1.addRecipe(RECIPE);
		CAT1.addRecipe(RECIPE2);
		CAT2.addRecipe(RECIPE);
		RECIPE.setCategories(categories);
		categories.remove(CAT2);
		RECIPE2.setCategories(categories);
	}

	@Test
	void setRecipes() {
		Set<Recipe> recipes = new HashSet<>();
		recipes.add(RECIPE);
		recipes.add(RECIPE2);
		recipes.add(RECIPE3);
		CAT1.setRecipes(recipes);
		assertEquals(3, CAT1.getRecipes().size());
	}

	@Test
	void addRecipe() {
		assertEquals(1, CAT2.getRecipes().size());
		assertFalse(CAT2.getRecipes().contains(RECIPE3));
		CAT2.addRecipe(RECIPE3);
		assertTrue(CAT2.getRecipes().contains(RECIPE3));
		CAT2.removeRecipe(RECIPE3);
		assertThrows(IllegalArgumentException.class, () ->  CAT1.addRecipe(null));
	}

	@Test
	void removeRecipe() {
		assertEquals(2, CAT1.getRecipes().size());
		CAT1.removeRecipe(RECIPE2);
		assertEquals(1, CAT1.getRecipes().size());
		CAT1.removeRecipe(RECIPE);
		assertEquals(0, CAT1.getRecipes().size());
		assertThrows(IllegalArgumentException.class, () -> CAT1.removeRecipe(null));
	}
}