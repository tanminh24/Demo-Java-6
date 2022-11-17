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

import sof306.ph18485.entities.Student;
import sof306.ph18485.repositories.StudentRepository;

@CrossOrigin("*")
@RestController
public class StudentRestController {

	@Autowired
	private StudentRepository studentRepo;

	@GetMapping("/api/students")
	public ResponseEntity<List<Student>> getAll() {
		return ResponseEntity.ok(studentRepo.findAll());
	}

	@GetMapping("/api/students/{email}")
	public ResponseEntity<Student> getOne(@PathVariable("email") String email) {
		if (!studentRepo.existsById(email)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(studentRepo.findById(email).get());
	}

	@PostMapping("/api/students")
	public ResponseEntity<Student> post(@RequestBody Student student) {
		if (studentRepo.existsById(student.getEmail())) {
			return ResponseEntity.badRequest().build();
		}
		studentRepo.save(student);
		return ResponseEntity.ok(student);
	}

	@PutMapping("/api/students/{email}")
	public ResponseEntity<Student> put(@PathVariable("email") String email, @RequestBody Student student) {
		if (!studentRepo.existsById(email)) {
			return ResponseEntity.notFound().build();
		}
		studentRepo.save(student);
		return ResponseEntity.ok(student);
	}

	@DeleteMapping("/api/students/{email}")
	public ResponseEntity<Void> delete(@PathVariable("email") String email) {
		if (!studentRepo.existsById(email)) {
			return ResponseEntity.notFound().build();
		}
		studentRepo.deleteById(email);
		return ResponseEntity.ok().build();
	}
}
