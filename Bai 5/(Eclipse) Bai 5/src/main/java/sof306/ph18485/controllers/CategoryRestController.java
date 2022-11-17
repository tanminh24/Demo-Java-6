package sof306.ph18485.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sof306.ph18485.entities.Category;
import sof306.ph18485.repositories.CategoryRepository;

@CrossOrigin("*")
@RestController
public class CategoryRestController {

	@Autowired
	private CategoryRepository categoryRepo;

	@GetMapping("/api/categories")
	public ResponseEntity<List<Category>> getAll() {
		return ResponseEntity.ok(categoryRepo.findAll());
	}

	@GetMapping("/api/categories/{id}")
	public ResponseEntity<Category> getOne(@PathVariable("id") Integer id) {
		if (!categoryRepo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoryRepo.findById(id).get());
	}

	@PostMapping("/api/categories")
	public ResponseEntity<Category> post(@RequestBody Category category) {
		if (categoryRepo.existsById(category.getId())) {
			return ResponseEntity.badRequest().build();
		}
		categoryRepo.save(category);
		return ResponseEntity.ok(category);
	}

	@PutMapping("/api/categories/{id}")
	public ResponseEntity<Category> put(@PathVariable("id") Integer id, @RequestBody Category category) {
		if (!categoryRepo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		categoryRepo.save(category);
		return ResponseEntity.ok(category);
	}

	@DeleteMapping("/api/categories/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		if (!categoryRepo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		categoryRepo.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
