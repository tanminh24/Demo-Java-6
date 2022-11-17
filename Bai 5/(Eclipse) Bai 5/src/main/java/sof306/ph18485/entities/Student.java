package sof306.ph18485.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
	
	@Id
	private String email;
	private String fullname;
	private Double marks;
	private Boolean gender;
	private String country;
}
