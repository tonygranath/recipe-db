package se.lexicon.tonygranath.recipedb.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.tonygranath.recipedb.model.Ingredient;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
	@Query("SELECT i FROM Ingredient i WHERE UPPER(i.name) = UPPER(:name)")
	Optional<Ingredient> findByNameIgnoreCase(@Param("name") String name);
	@Query("SELECT i FROM Ingredient i WHERE UPPER(i.name) LIKE CONCAT('%', UPPER(:name), '%')")
	List<Ingredient> findByNameContainsIgnoreCase(@Param("name") String search);
}
