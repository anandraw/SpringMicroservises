package com.anand.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.anand.model.Inventory;
import com.anand.repo.Inventoryreo;

@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	private Inventoryreo inventoryreo;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Inventory inventory = new Inventory();
		inventory.setSkuCode("iphone_13");
		inventory.setQuantity(100);

		Inventory inventory1 = new Inventory();
		inventory1.setSkuCode("iphone_13_red");
		inventory1.setQuantity(0);

		inventoryreo.save(inventory);
		inventoryreo.save(inventory1);

	}

}
