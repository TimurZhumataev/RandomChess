package org.example.randomchess.Controllers;

import org.example.randomchess.Services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/admin")
public class AdminController {

    final
    MyUserService myUserService;

    public AdminController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @GetMapping("/all")
    public void getAllUsers(){
        myUserService.getAllUsers();
    }
}
