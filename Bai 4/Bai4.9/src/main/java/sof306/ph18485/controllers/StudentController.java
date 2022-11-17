package sof306.ph18485.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sof306.ph18485.beans.Student;
import sof306.ph18485.beans.StudentMap;
import sof306.ph18485.repositories.StudentRepository;

@Controller
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepo;

	@RequestMapping("/student/index")
	public String index(Model model) {
		Student student = new Student();
		student.setEmail("");
		student.setFullname("");
		student.setMarks(0.0);
		student.setGender(true);
		student.setCountry("VN");
		StudentMap map = studentRepo.findAll();
		model.addAttribute("form", student);
		model.addAttribute("items", map);
		return "student/index";
	}
	
	@RequestMapping("/student/edit/{key}")
	public String edit(Model model, @PathVariable("key") String key) {
		model.addAttribute("key", key);
		Student student = studentRepo.findByKey(key);
		StudentMap map = studentRepo.findAll();
		model.addAttribute("form", student);
		model.addAttribute("items", map);
		return "student/index";
	}
	
	@RequestMapping("/student/create")
	public String create(Student student) {
		studentRepo.create(student);
		return "redirect:/student/index";
	}
	
	@RequestMapping("/student/update/{key}")
	public String update(@PathVariable("key") String key, Student student) {
		studentRepo.update(key, student);
		return "redirect:/student/edit/" + key;
	}
	
	@RequestMapping("/student/delete/{key}")
	public String delete(@PathVariable("key") String key) {
		studentRepo.delete(key);
		return "redirect:/student/index";
	}
}
