package com.blog.app.payLoads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	
	 @NotNull(message = "Name should not be null")
	 @NotBlank(message = "Name should not be blank")
	 @Size(min = 2, max = 25, message = "Name should be between 2 and 25 characters")
	 private String name;
	
	 @Column(unique= true)
	 @Email(message = "Email should be in proper format")
	 private String email;
	 
	 @NotNull(message = "Name should not be null")
	 @NotBlank(message = "Name should not be blank")
	 @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}$",
     message = "Password must contain at least one letter, one digit, and have a length between 6 and 10 characters")
	 private String password;
	 
//		private Set<RoleDto> roles = new HashSet<>();
	 
	 @JsonIgnore
		public String getPassword() {
			return this.password;
		}
		
		@JsonProperty
		public void setPassword(String password) {
			this.password=password;
		}

}
