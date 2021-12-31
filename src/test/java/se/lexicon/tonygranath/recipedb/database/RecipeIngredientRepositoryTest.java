package se.lexicon.tonygranath.recipedb.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.tonygranath.recipedb.model.Ingredient;
import se.lexicon.tonygranath.recipedb.model.Measurement;
import se.lexicon.tonygranath.recipedb.model.RecipeIngredient;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RecipeIngredientRepositoryTest {
	@Autowired
	TestEntityManager em;
	@Autowired
	RecipeIngredientRepository repository;

	private Ingredient ingredient = new Ingredient("Test ingredient");
	private RecipeIngredient recipeIngredient = new RecipeIngredient(ingredient, 1.0, Measurement.g);

	@BeforeEach
	void setUp() {
		repository.save(recipeIngredient);
	}

	@Test
	void test() {
		System.out.println(recipeIngredient);
	}
}