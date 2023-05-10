package bg.softuni.pizzashop.repository;

import bg.softuni.pizzashop.model.entity.Ingredient;
import bg.softuni.pizzashop.model.entity.enums.IngredientTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Optional<Ingredient> findByName(String name);

    @Query("SELECT i FROM Ingredient i WHERE i.ingredientType = :mainType")
    List<Ingredient> findAllMainIngredientsOrderByName(@Param("mainType") IngredientTypeEnum mainType);
}
