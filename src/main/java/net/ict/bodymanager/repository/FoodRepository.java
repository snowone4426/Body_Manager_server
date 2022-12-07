package net.ict.bodymanager.repository;

import net.ict.bodymanager.entity.Food;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    @EntityGraph(attributePaths = {"food_img"})
    @Query("select f from Food f where f.food_id=:food_id")
    Optional<Food> findByIdWithImages(Long food_id);


}
