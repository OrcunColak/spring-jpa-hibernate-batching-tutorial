package com.colak.springtutorial.service;

import com.colak.springtutorial.jpa.Author;
import com.colak.springtutorial.repository.AuthorRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataService {

    private final AuthorRepository authorRepository;

    private final EntityManager entityManager;

    // When handling a large number of records, it's recommended to flush and clear the persistence context periodically to avoid memory issues.
    @Transactional
    public void insertAuthorList(List<Author> authorsList, int batchSize) {
        for (int i = 0; i < authorsList.size(); i++) {
            authorRepository.save(authorsList.get(i));

            if (i % batchSize == 0 && i > 0) {
                authorRepository.flush();
                entityManager.clear();
            }
        }
    }
}

