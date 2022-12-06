package com.store.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entities.Product;
import com.store.reponsitories.ProductRepository;
import com.store.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepo;

	@Override
	public List<Product> findAll() {
		return productRepo.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return productRepo.findById(id).get();
	}

	@Override
	public List<Product> findByCategoryId(Integer cid) {
		return productRepo.findByCategoryId(cid);
	}

	@Override
	public Product create(Product product) {
		return productRepo.save(product);
	}

	@Override
	public Product update(Product product) {
		return productRepo.save(product);
	}

	@Override
	public void delete(Integer id) {
		productRepo.deleteById(id);
		;
	}

}
