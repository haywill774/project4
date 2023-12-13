package com.example.New_project.repository;

import com.example.New_project.model.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerServiceRepo extends JpaRepository<BorrowedBook, Long> {


}
