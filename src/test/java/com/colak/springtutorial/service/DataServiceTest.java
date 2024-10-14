package com.colak.springtutorial.service;

import com.colak.springtutorial.jpa.Author;
import com.colak.springtutorial.jpa.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DataServiceTest {

    @Autowired
    private DataService dataService;

    @Test
    void insertAuthorList() {
        List<Author> authorsList = new ArrayList<>();
        for (int authorIndex = 1; authorIndex <= 50; authorIndex++) {
            Author author = new Author("Owner " + authorIndex);
            authorsList.add(author);

            List<Book> books = author.getBooks();
            for (int bookIndex = 1; bookIndex <= 3; bookIndex++) {
                Book book = new Book("Book " + bookIndex + " of Author " + authorIndex, author);
                books.add(book);
            }
        }
        dataService.insertAuthorList(authorsList, 20);
    }
}