package org.ngram.search.service;

import org.ngram.search.entity.NGram;
import org.ngram.search.model.PaginationRequestDto;
import org.springframework.http.ResponseEntity;

public interface NGramService {
    ResponseEntity<?> searchNGram(String keyword);
    ResponseEntity<?> fetchRecords();
    ResponseEntity<?> fetchRecordsWithPaginationFilterSorting(PaginationRequestDto paginationRequestDto, NGram filterDto);
}
