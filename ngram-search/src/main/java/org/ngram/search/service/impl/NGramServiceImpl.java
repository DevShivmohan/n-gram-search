package org.ngram.search.service.impl;

import org.ngram.search.repository.NGramRepository;
import org.ngram.search.service.NGramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NGramServiceImpl implements NGramService {
    @Autowired
    private NGramRepository nGramRepository;
    @Override
    public ResponseEntity<?> searchNGram(String keyword) {
        if(keyword==null || keyword.isBlank())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Search parameter could not be null or blank");
        return ResponseEntity.status(HttpStatus.OK).body(nGramRepository.findByNgram(keyword));
    }

    @Override
    public ResponseEntity<?> fetchRecords() {
        return ResponseEntity.status(HttpStatus.OK).body(nGramRepository.findAll());
    }
}