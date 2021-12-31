package se.lexicon.tonygranath.recipedb.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.tonygranath.recipedb.model.Ingredient;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class IngredientRepositoryTest {
	@Autowired
	private IngredientRepository repository;
	private static final String NAME1 = "Potato;";
	private static final String NAME2 = "Tomato";
	private static final String NAME3 = "Milk";
	private Ingredient ingredient1 = new Ingredient(NAME1);
	private Ingredient ingredient2 = new Ingredient(NAME2);
	private Ingredient ingredient3 = new Ingredient(NAME3);

	@Autowired
	TestEntityManager em;

	@BeforeEach
	void setUp() {
		repository.save(ingredient1);
		repository.save(ingredient2);
		repository.save(ingredient3);
	}

	@Test
	void findByNameIgnoreCase() {
		assertEquals(NAME1, repository.findByNameIgnoreCase(NAME1).get().getName());
		assertEquals(NAME2, repository.findByNameIgnoreCase(NAME2.toUpperCase()).get().getName());
		assertEquals(NAME3, repository.findByNameIgnoreCase(NAME3).get().getName());
	}

	@Test
	void findByNameContainsIgnoreCase() {
		assertEquals(2, repository.findByNameContainsIgnoreCase("ATO").size());
	}
}