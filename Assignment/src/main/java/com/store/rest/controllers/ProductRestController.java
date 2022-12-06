package com.store.rest.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.entities.Product;
import com.store.services.ExcelExportService;
import com.store.services.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ExcelExportService excelService;

	@GetMapping("{id}")
	public Product getOne(@PathVariable("id") Integer id) {
		return productService.findById(id);
	}

	@GetMapping()
	public List<Product> getAll() {
		return productService.findAll();
	}

	@PostMapping
	public Product create(@RequestBody Product product) {
		return productService.create(product);
	}

	@PutMapping("{id}")
	public Product update(@PathVariable("id") Integer id, @RequestBody Product product) {
		return productService.update(product);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		productService.delete(id);
	}
	
	@RequestMapping("/export/excel")
	public void exportExcel() throws IOException {
		System.out.println("Start");
		excelService.export();
		System.out.println("End");
	}

}
