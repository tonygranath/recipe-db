package se.lexicon.tonygranath.recipedb.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.tonygranath.recipedb.model.Recipe;

import java.util.List;
import java.util.Set;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {
	@Query("SELECT r FROM Recipe r WHERE UPPER(r.name) LIKE UPPER(CONCAT('%', :name, '%'))")
	List<Recipe> findByNameContains(@Param("name") String name);
	@Query("SELECT r FROM Recipe r, RecipeIngredient ri, Ingredient i " +
			"WHERE i = ri.ingredient AND ri MEMBER OF r.ingredients AND i.name = :name")
	List<Recipe> findByIngredientName(@Param("name") String name);
	@Query("SELECT r FROM Recipe r INNER JOIN r.categories c WHERE UPPER(c.name) = UPPER(:category)")
	List<Recipe> findByCategory(@Param("category") String category);
	@Query("SELECT r FROM Recipe r INNER JOIN r.categories c WHERE c.name IN :categories")
	List<Recipe> findByCategoryInSet(@Param("categories") Set<String> categories);
}
