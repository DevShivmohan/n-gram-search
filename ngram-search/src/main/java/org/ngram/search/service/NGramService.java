package org.ngram.search.service;

import org.springframework.http.ResponseEntity;

public interface NGramService {
    ResponseEntity<?> searchNGram(String keyword);
    ResponseEntity<?> fetchRecords();
}
