package com.colak.springtutorial.repository;

import com.colak.springtutorial.jpa.Author;
import com.colak.springtutorial.jpa.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
