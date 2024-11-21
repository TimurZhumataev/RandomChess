package org.example.randomchess.Services;

import org.example.randomchess.Repositories.MyUserRepository;
import org.example.randomchess.Models.MyUser;
import org.example.randomchess.Models.MyUserDTO;
import org.example.randomchess.Models.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MyUserService {
    final MyUserRepository myUserRepository;
    final PasswordEncoder passwordEncoder;


    public MyUserService(MyUserRepository myUserRepository, PasswordEncoder passwordEncoder) {
        this.myUserRepository = myUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> createUser(MyUserDTO myUserDTO) {
        Optional<MyUser> myUser = myUserRepository.findByUsername(myUserDTO.getUsername());
        if (myUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this credentials already exists");
        }
        MyUser newUser = new MyUser();
        newUser.setUsername(myUserDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(myUserDTO.getPassword()));
        newUser.setEmail(myUserDTO.getEmail());
        newUser.setRole(Role.USER);
        myUserRepository.save(newUser);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    public ResponseEntity<MyUser> getUserByUsername(String username) {
        Optional<MyUser> myUser = myUserRepository.findByUsername(username);
        return myUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<MyUser>> getAllUsers() {
        List<MyUser> myUsers = myUserRepository.findAll();
        if(myUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteUserByUsername(String username) {
        Optional<MyUser> myUser = myUserRepository.findByUsername(username);
        if(myUser.isPresent()) {
            myUserRepository.delete(myUser.get());
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<MyUser> updateUserByUsername(String username, MyUserDTO myUserDTO) {
        Optional<MyUser> myUser = myUserRepository.findByUsername(username);
        if(myUser.isPresent()) {
            myUser.get().setUsername(myUserDTO.getUsername());
            myUser.get().setPassword(passwordEncoder.encode(myUserDTO.getPassword()));
            myUser.get().setEmail(myUserDTO.getEmail());
            myUser.get().setRole(Role.USER);
            myUserRepository.save(myUser.get());
            return new ResponseEntity<>(myUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
