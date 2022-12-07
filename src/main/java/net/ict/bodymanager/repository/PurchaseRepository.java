package net.ict.bodymanager.repository;

import net.ict.bodymanager.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

//    @Query(value = "select date_format(o.created_at, '%Y-%c-%d') as created_at, pr.price_name, pr.price_info from purchase p join order_info o on p.order_id = o.order_id join price pr on p.price_id = pr.price_id where o.member_id = 4" , nativeQuery = true)
//    List<Purchase> purchaseList;

//    @Query(value = "")

}
