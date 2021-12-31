package se.lexicon.tonygranath.recipedb.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.tonygranath.recipedb.model.RecipeInstruction;

@Repository
public interface RecipeInstructionRepository extends JpaRepository<RecipeInstruction, String> {

}
