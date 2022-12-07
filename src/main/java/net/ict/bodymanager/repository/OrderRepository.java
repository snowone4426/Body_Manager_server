package net.ict.bodymanager.repository;

import net.ict.bodymanager.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderInfo, Long>  {


}
