package org.ngram.search.controller;

import lombok.extern.slf4j.Slf4j;
import org.ngram.search.service.NGramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ngram")
@Slf4j
public class NGramController {
    @Autowired
    private NGramService nGramService;
    @GetMapping("/{keyword}")
    public ResponseEntity<?> findByNgramSearch(@PathVariable("keyword") String keyword){
        return nGramService.searchNGram(keyword);
    }

    @GetMapping
    public ResponseEntity<?> findAllRecords(){
        return nGramService.fetchRecords();
    }
}
