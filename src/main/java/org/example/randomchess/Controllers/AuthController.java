package org.example.randomchess.Controllers;

import org.example.randomchess.Models.MyUserDTO;
import org.example.randomchess.Services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    final
    MyUserService myUserService;

    public AuthController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody MyUserDTO myUserDTO) {
        myUserService.createUser(myUserDTO);
    }
}
