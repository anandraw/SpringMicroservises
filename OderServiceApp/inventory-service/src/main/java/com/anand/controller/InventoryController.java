package com.anand.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.anand.dto.Inventoryresponce;
import com.anand.service.InventoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<Inventoryresponce> isInStock(@RequestParam List<String> skuCode) {
		log.info("Received inventory check request for SKUs: {}", skuCode);
		return inventoryService.isInStock(skuCode);
	}

	@GetMapping("/inStock/{skuCode}")
	public ResponseEntity<Inventoryresponce> checkStock(@PathVariable String skuCode) {
		Inventoryresponce response = inventoryService.inInStock(skuCode);
		return ResponseEntity.ok(response);
	}

}
