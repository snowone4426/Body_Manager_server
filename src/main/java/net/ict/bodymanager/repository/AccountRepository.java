package net.ict.bodymanager.repository;

import net.ict.bodymanager.entity.OrderInfo;
import net.ict.bodymanager.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<OrderInfo, Long> {

}
