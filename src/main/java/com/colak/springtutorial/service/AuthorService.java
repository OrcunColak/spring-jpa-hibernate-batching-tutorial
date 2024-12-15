package com.colak.springtutorial.service;

import com.colak.springtutorial.jpa.Author;
import com.colak.springtutorial.repository.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final EntityManager entityManager;

    private final TransactionTemplate transactionTemplate;

    private final ObjectMapper objectMapper;

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

    public StreamingResponseBody getAuthorStream() {
        return outputStream ->
                transactionTemplate.execute(
                        new TransactionCallbackWithoutResult() {
                            @Override
                            protected void doInTransactionWithoutResult(TransactionStatus status) {
                                fillStream(outputStream);
                            }
                        });
    }

    private void fillStream(OutputStream outputStream) {
        try (var authorStream = authorRepository.findAllBy()) {
            authorStream.forEach(
                    author -> {
                        try {
                            var json = objectMapper.writeValueAsString(author);
                            byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
                            outputStream.write(bytes);
                            entityManager.detach(author);

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }

}

