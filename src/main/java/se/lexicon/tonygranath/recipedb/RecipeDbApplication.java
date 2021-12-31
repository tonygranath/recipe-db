package se.lexicon.tonygranath.recipedb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.lexicon.tonygranath.recipedb.database.IngredientRepository;
import se.lexicon.tonygranath.recipedb.database.RecipeIngredientRepository;
import se.lexicon.tonygranath.recipedb.database.RecipeInstructionRepository;
import se.lexicon.tonygranath.recipedb.database.RecipeRepository;
import se.lexicon.tonygranath.recipedb.model.*;

@SpringBootApplication
public class RecipeDbApplication {
	public static void main(String[] args) {
		SpringApplication.run(RecipeDbApplication.class, args);

	}
}
