package com.colak.springtutorial.service;

import com.colak.springtutorial.jpa.Author;
import com.colak.springtutorial.jpa.Book;
import com.colak.springtutorial.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataService {

    private final AuthorRepository authorRepository;


    @Transactional
    public void insertData() {

        List<Author> authorsList = new ArrayList<>();

        // Batch size is defined in application.properties/yml
        for (int authorIndex = 1; authorIndex <= 50; authorIndex++) {
            Author author = new Author("Owner " + authorIndex);

            List<Book> books = author.getBooks();
            // Each person can own multiple cats
            for (int bookIndex = 1; bookIndex <= 3; bookIndex++) {
                Book book = new Book("Book " + bookIndex + " of Author " + authorIndex, author);
                books.add(book);
            }

            authorsList.add(author);

            // Flush and clear session periodically to control memory usage
            if (authorIndex % 20 == 0) { // Adjust based on the batch size
                authorRepository.saveAll(authorsList);
                authorRepository.flush();
                authorsList.clear();
            }
        }
    }
}

