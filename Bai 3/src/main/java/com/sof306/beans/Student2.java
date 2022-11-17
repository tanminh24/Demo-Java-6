package com.sof306.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student2 {

	@NotBlank(message="Không để trống email")
	@Email(message="Không đúng định dạng email")
	String email;
	
	@NotBlank(message="Không để trống họ tên")
	String fullname;
	
	@NotNull(message="Không để trống điểm")
	@PositiveOrZero(message="Điểm phải lớn hơn hoặc bằng 0")
	@Max(value=10, message="Điểm phải nhỏ hơn hoặc bằng 10")
	Double marks;
	
	@NotNull(message="Chọn giới tính")
	Boolean gender;
	
	@NotBlank(message="Chọn quốc tịch")
	String country;
	
}
