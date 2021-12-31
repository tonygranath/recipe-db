package se.lexicon.tonygranath.recipedb.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.tonygranath.recipedb.model.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RecipeRepositoryTest {
	@Autowired
	private TestEntityManager em;
	@Autowired
	private RecipeRepository repository;
	@Autowired
	private RecipeIngredientRepository recipeIngredientRepository;

	//probably not the most beautiful test I've written ...

	private final Measurement unit = Measurement.g;
	private static final String CATEGORY1 = "Kött";
	private static final String CATEGORY2 = "Test";
	private static final String CATEGORY3 = "Fisk";
	private static final String CATEGORY4 = "Växtbaserat";
	private static final String INGREDIENT1 = "Lök";
	private static final String INGREDIENT2 = "Hästkött";
	private static final String INGREDIENT3 = "Vatten";
	private static final String INGREDIENT4 = "Tomat";
	private static final String RECIPE1 = "Häst med lök";
	private static final String RECIPE2 = "Test";
	private static final String RECIPE3 = "Pannkakstårta";
	private static final String RECIPE4 = "Testkaka";
	private RecipeCategory category1, category2, category3, category4;
	private Set<RecipeCategory> categorySet1 = new HashSet<>();
	private Set<RecipeCategory> categorySet2 = new HashSet<>();
	private Set<RecipeCategory> categorySet3 = new HashSet<>();
	private Set<RecipeCategory> categorySet4 = new HashSet<>();
	private RecipeInstruction instruction1, instruction2, instruction3, instruction4;
	private Ingredient ingredient1, ingredient2, ingredient3, ingredient4;
	private RecipeIngredient rIngredient1, rIngredient2, rIngredient3, rIngredient4,
							 rIngredient5, rIngredient6, rIngredient7, rIngredient8,
							 rIngredient9, rIngredient10;
	private List<RecipeIngredient> ingredientList = new ArrayList<>();
	private List<RecipeIngredient> ingredientList2 = new ArrayList<>();
	private List<RecipeIngredient> ingredientList3 = new ArrayList<>();
	private List<RecipeIngredient> ingredientList4 = new ArrayList<>();
	private Recipe recipe1, recipe2, recipe3, recipe4;

	@BeforeEach
	void setUp() {
		category1 = new RecipeCategory(CATEGORY1);
		category2 = new RecipeCategory(CATEGORY2);
		category3 = new RecipeCategory(CATEGORY3);
		category4 = new RecipeCategory(CATEGORY4);
		categorySet1.add(category1);
		categorySet1.add(category2);
		categorySet2.add(category2);
		categorySet2.add(category3);
		categorySet3.add(category3);
		categorySet3.add(category4);
		categorySet4.add(category1);
		categorySet4.add(category2);
		categorySet4.add(category4);

		instruction1 = new RecipeInstruction("Do this, and then do that.");
		instruction2 = new RecipeInstruction("Test, test, test.");
		instruction3 = new RecipeInstruction("Blablabla");
		instruction4 = new RecipeInstruction("123");

		ingredient1 = new Ingredient(INGREDIENT1);
		ingredient2 = new Ingredient(INGREDIENT2);
		ingredient3 = new Ingredient(INGREDIENT3);
		ingredient4 = new Ingredient(INGREDIENT4);
		ingredient1 = em.persist(ingredient1);
		ingredient2 = em.persist(ingredient2);
		ingredient3 = em.persist(ingredient3);
		ingredient4 = em.persist(ingredient4);

		rIngredient1 = new RecipeIngredient(
			ingredient1,
			250.0,
			unit,
			null
		);
		rIngredient2 = new RecipeIngredient(
				ingredient2,
				1000.5,
				unit,
				null
		);
		rIngredient3 = new RecipeIngredient(
				ingredient3,
				3.5,
				Measurement.dl,
				null
		);
		rIngredient4 = new RecipeIngredient(
				ingredient4,
				1.0,
				Measurement.hg,
				null
		);
		rIngredient5 = new RecipeIngredient(
				ingredient1,
				250.0,
				unit,
				null
		);
		rIngredient6 = new RecipeIngredient(
				ingredient2,
				1000.5,
				unit,
				null
		);
		rIngredient7 = new RecipeIngredient(
				ingredient3,
				3.5,
				Measurement.dl,
				null
		);
		rIngredient8 = new RecipeIngredient(
				ingredient4,
				1.0,
				Measurement.hg,
				null
		);
		rIngredient9 = new RecipeIngredient(
				ingredient1,
				0.25,
				Measurement.tsp,
				null
		);
		rIngredient10 = new RecipeIngredient(
				ingredient2,
				200.0,
				Measurement.tbsp,
				null
		);
		ingredientList.add(rIngredient1);
		ingredientList.add(rIngredient2);
		ingredientList2.add(rIngredient3);
		ingredientList2.add(rIngredient4);
		ingredientList3.add(rIngredient5);
		ingredientList3.add(rIngredient6);
		ingredientList4.add(rIngredient7);
		ingredientList4.add(rIngredient8);
		ingredientList4.add(rIngredient9);
		ingredientList4.add(rIngredient10);

		/*rIngredient1 = em.persist(rIngredient1);
		rIngredient2 = em.persist(rIngredient2);
		rIngredient3 = em.persist(rIngredient3);
		rIngredient4 = em.persist(rIngredient4);
		rIngredient5 = em.persist(rIngredient5);
		rIngredient6 = em.persist(rIngredient6);
		rIngredient7 = em.persist(rIngredient7);
		rIngredient8 = em.persist(rIngredient8);
		rIngredient9 = em.persist(rIngredient9);
		rIngredient10 = em.persist(rIngredient10);*/
		rIngredient1 = recipeIngredientRepository.save(rIngredient1);
		rIngredient2 = recipeIngredientRepository.save(rIngredient2);
		rIngredient3 = recipeIngredientRepository.save(rIngredient3);
		rIngredient4 = recipeIngredientRepository.save(rIngredient4);
		rIngredient5 = recipeIngredientRepository.save(rIngredient5);
		rIngredient6 = recipeIngredientRepository.save(rIngredient6);
		rIngredient7 = recipeIngredientRepository.save(rIngredient7);
		rIngredient8 = recipeIngredientRepository.save(rIngredient8);
		rIngredient9 = recipeIngredientRepository.save(rIngredient9);
		rIngredient10 = recipeIngredientRepository.save(rIngredient10);

		recipe1 = new Recipe(RECIPE1, ingredientList, instruction1, categorySet1);
		recipe2 = new Recipe(RECIPE2, ingredientList2, instruction2, categorySet2);
		recipe3 = new Recipe(RECIPE3, ingredientList3, instruction3, categorySet3);
		recipe4 = new Recipe(RECIPE4, ingredientList4, instruction4, categorySet4);

		recipe1 = repository.save(recipe1);
		recipe2 = repository.save(recipe2);
		recipe3 = repository.save(recipe3);
		recipe4 = repository.save(recipe4);

		rIngredient1.setRecipe(recipe1);
		rIngredient2.setRecipe(recipe1);
		rIngredient3.setRecipe(recipe2);
		rIngredient4.setRecipe(recipe2);
		rIngredient5.setRecipe(recipe3);
		rIngredient6.setRecipe(recipe3);
		rIngredient7.setRecipe(recipe4);
		rIngredient8.setRecipe(recipe4);
		rIngredient9.setRecipe(recipe4);
		rIngredient10.setRecipe(recipe4);
	}

	@Test
	void autoRemoveRecipeIngredient() {
		assertEquals(10, recipeIngredientRepository.findAll().size());
		for(Recipe r : repository.findAll()) {
			if (r.getIngredients().contains(rIngredient1)) {
				r.removeIngredient(rIngredient1);
				repository.save(r);
			}
			if (r.getIngredients().contains(rIngredient4)) {
				r.removeIngredient(rIngredient4);
				repository.save(r);
			}
		}
		assertEquals(8, recipeIngredientRepository.findAll().size());
	}

	@Test
	void findByNameContains() {
		assertEquals(3, repository.findByNameContains("e").size());
		assertEquals(1, repository.findByNameContains(RECIPE1.toUpperCase()).size());
	}

	@Test
	void findByIngredientName() {
		assertEquals(3, repository.findByIngredientName(INGREDIENT1).size());
	}

	@Test
	void findByCategory() {
		assertEquals(2, repository.findByCategory(CATEGORY1).size());
	}

	@Test
	void findByCategoryInSet() {
		Set<String> words = new HashSet<>();
		words.add("test");
		words.add("häst");
		words.add(CATEGORY1);
		words.add("123");
		assertEquals(2, repository.findByCategoryInSet(words).size());
	}
}