package com.colak.springtutorial.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataServiceTest {

    @Autowired
    private DataService dataService;
    @Test

    void insertAuthorList() {
        dataService.insertAuthorList();
    }
}