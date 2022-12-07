package net.ict.bodymanager.repository;

import net.ict.bodymanager.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {


}
