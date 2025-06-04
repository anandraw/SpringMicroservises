package com.anand.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.anand.model.Inventory;

public interface Inventoryreo extends JpaRepository<Inventory, Long> {

	List<Inventory> findBySkuCodeIn(List<String> skuCode);
	Inventory findBySkuCode(String skucode);

}
