package sof306.ph18485.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "fullname", nullable = false)
	private String fullname;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "photo")
	private String photo;
	
	@Column(name = "activated", nullable = false)
	private Integer activated;
	
	@Column(name = "role", nullable = false)
	private AccountRole role;
	
	@Column(name = "reset_password_token")
	private String resetPasswordToken;

	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Order> orders;
	
}
