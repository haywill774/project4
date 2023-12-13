package com.example.New_project.controller;


import com.example.New_project.model.LibraryUser;
import com.example.New_project.service.LibraryServices;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class LibraryController {

    private final LibraryServices libraryServices;

    public LibraryController(LibraryServices libraryServices) {
        this.libraryServices = libraryServices;
    }

    @GetMapping("/users")
    public ResponseEntity<List<LibraryUser>> getAllUsers(){
        return new ResponseEntity<>(libraryServices.getAllUsers().getBody(), HttpStatus.OK);
    }
    @PostMapping("/addUsers")
    public ResponseEntity<LibraryUser> addUser(@Valid @RequestBody LibraryUser libraryUser ){
        return ResponseEntity.ok(libraryServices.addUsers(libraryUser).getBody());
    }
    @PostMapping("/allUsers")
    public ResponseEntity<Map<String,Boolean>> saveAllUsers ( @RequestBody List<LibraryUser> libraryUser){
        return new ResponseEntity<>(libraryServices.saveAll(libraryUser),HttpStatus.CREATED);
    }
    @GetMapping("/users/email/{email}")
    public List<LibraryUser> findUserByEmail(@PathVariable String email ){
        return libraryServices.findUserByEmail(email);
    }
    @GetMapping("/users/name/{fullName}")
    public List<LibraryUser> findUserByFullName(@PathVariable String fullName ){
        return libraryServices.findUserByFullName(fullName);
    }

    @DeleteMapping("/users/{id}")
public ResponseEntity<LibraryUser> deleteUser (@PathVariable long id){
        return ResponseEntity.ok(libraryServices.deleteUser(id));
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUsers (@PathVariable long id, @RequestBody LibraryUser libraryUser){
        return ResponseEntity.ok(libraryServices.updateUsers(id,libraryUser));
    }
}
