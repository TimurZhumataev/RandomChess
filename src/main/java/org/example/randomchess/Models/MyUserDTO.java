package org.example.randomchess.Models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MyUserDTO {
    @NotBlank(message = "username cannot be empty")
    @Size(min = 3, max = 15, message = "username must be between 3 and 15 characters")
    private String username;
    @NotBlank(message = "password cannot be empty")
    @Size(min = 3, max = 15, message = "password must be between 3 and 15 characters")
    private String password;
    @NotBlank(message = "email cannot be empty")
    @Email(message = "email should be valid")
    private String email;
    public MyUserDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
