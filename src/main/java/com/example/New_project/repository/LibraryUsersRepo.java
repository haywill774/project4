package com.example.New_project.repository;

import com.example.New_project.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryUsersRepo extends JpaRepository <LibraryUser, Long> {

   List <LibraryUser> findUserByFullName(String fullName);
   List <LibraryUser> findUserByEmail(String email);
}
