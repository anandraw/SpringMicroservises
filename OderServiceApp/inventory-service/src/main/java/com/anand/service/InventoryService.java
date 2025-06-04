package com.anand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anand.dto.Inventoryresponce;
import com.anand.model.Inventory;
import com.anand.repo.Inventoryreo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryService {

	@Autowired
	private Inventoryreo inventoryreo;

	@Transactional(readOnly = true)
	@SneakyThrows
	public List<Inventoryresponce> isInStock(List<String> skuCodes) {
		log.info("Checking inventory for SKUs: {}", skuCodes);
		log.info("Wait Started..");
		Thread.sleep(10000);
		log.info("Wait Ended");

		// Fetch inventory data for all SKUs
		List<Inventory> inventories = inventoryreo.findBySkuCodeIn(skuCodes);

		// Map to InventoryResponse objects
		return inventories.stream().map(inventory -> Inventoryresponce.builder().skuCode(inventory.getSkuCode())
				.isInStock(inventory.getQuantity() > 0).build()).toList();
	}

	public Inventoryresponce inInStock(String skuCode) {
		log.info("Checking single item in inventory for SKU: {}", skuCode);

		// Fetch inventory data by SKU code
		Inventory inventory = inventoryreo.findBySkuCode(skuCode);

		// Check if the item exists and is in stock
		boolean isInStock = (inventory != null && inventory.getQuantity() > 0);

		// Build and return the response
		return new Inventoryresponce(skuCode, isInStock);
	}

}
