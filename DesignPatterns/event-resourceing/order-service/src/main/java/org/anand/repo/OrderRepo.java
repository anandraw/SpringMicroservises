package org.anand.repo;

import org.anand.entity.OrderEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<OrderEvent, String> {
}
