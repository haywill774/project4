package com.example.New_project.service;

import com.example.New_project.exception.BookNotFoundException;
import com.example.New_project.model.LibraryUser;

import com.example.New_project.repository.LibraryUsersRepo;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class LibraryServices {

//    @Autowired
    private final LibraryUsersRepo libraryUsersRepo;

    public LibraryServices(LibraryUsersRepo libraryUsersRepo) {
        this.libraryUsersRepo = libraryUsersRepo;
    }

    @PostMapping("/addUsers")
    public ResponseEntity<LibraryUser> addUsers (@Valid LibraryUser libraryUser){
        return ResponseEntity.ok( libraryUsersRepo.save(libraryUser));
    }
    public Map<String, Boolean> saveAll (List<LibraryUser> libraryUser){
        Map<String, Boolean> response = new HashMap<>();
        for (LibraryUser users : libraryUser)
            response.put(users.getFullName() + "posted successfully", true);
        libraryUsersRepo.saveAll(libraryUser);
        return response;
    }

    public ResponseEntity <List<LibraryUser>> getAllUsers (){

        return ResponseEntity.ok(libraryUsersRepo.findAll());
    }

    public LibraryUser findUserById (long id){
        return libraryUsersRepo.findById(id).orElseThrow(()-> new  BookNotFoundException("book not found"));
    }

    public List<LibraryUser> findUserByFullName (String fullName){
        return libraryUsersRepo.findUserByFullName(fullName);
    }
    public List<LibraryUser> findUserByEmail (String email){
        return libraryUsersRepo.findUserByEmail(email);
    }
    public String updateUsers (long id, LibraryUser libraryUser){
        LibraryUser user = findUserById(id);
        user.setFullName(libraryUser.getFullName());
        user.setEmail(libraryUser.getEmail());
        user.setAge(libraryUser.getAge());
        user.setAddress(libraryUser.getAddress());

        libraryUsersRepo.save(user);
        return "saved successfully";
    }

    public LibraryUser deleteUser (long id){
        LibraryUser user = findUserById(id);
        libraryUsersRepo.delete(user);
        return user;
    }
}
