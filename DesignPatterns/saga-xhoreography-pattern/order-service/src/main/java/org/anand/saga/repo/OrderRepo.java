package org.anand.saga.repo;

import org.anand.saga.entity.PurchesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<PurchesOrder,Integer> {
}
