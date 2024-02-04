package org.ngram.search.service.impl;

import org.ngram.search.entity.NGram;
import org.ngram.search.model.PaginationRequestDto;
import org.ngram.search.model.PaginationResponseDto;
import org.ngram.search.model.SortOrder;
import org.ngram.search.repository.NGramRepository;
import org.ngram.search.service.NGramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NGramServiceImpl implements NGramService {
    @Autowired
    private NGramRepository nGramRepository;

    @Override
    public ResponseEntity<?> searchNGram(String keyword) {
        if (keyword == null || keyword.isBlank())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Search parameter could not be null or blank");
        return ResponseEntity.status(HttpStatus.OK).body(nGramRepository.findByNgram(keyword));
    }

    @Override
    public ResponseEntity<?> fetchRecords() {
        return ResponseEntity.status(HttpStatus.OK).body(nGramRepository.findAll());
    }

    @Override
    public ResponseEntity<?> fetchRecordsWithPaginationFilterSorting(final PaginationRequestDto paginationRequestDto, final NGram filterDto) {
        final Sort.Direction direction = (paginationRequestDto.getSortOrder() == null || paginationRequestDto.getSortOrder() == SortOrder.ASC) ? Sort.Direction.ASC : Sort.Direction.DESC;
        final PaginationResponseDto paginationResponseDto = new PaginationResponseDto();
        final PageRequest pageRequest = PageRequest.of(paginationRequestDto.getPageNumber(), paginationRequestDto.getPageSize(), direction, paginationRequestDto.getSortColumn());
        if (filterDto == null) {
            final Page<NGram> page = nGramRepository.findAll(pageRequest);
            mapPageToEntity(page, paginationResponseDto);
            return ResponseEntity.status(HttpStatus.OK).body(paginationResponseDto);
        }
        final ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny();
        final Example<NGram> example = Example.of(filterDto, customExampleMatcher);
        final Page<NGram> page = nGramRepository.findAll(example, pageRequest);
        mapPageToEntity(page, paginationResponseDto);
        return ResponseEntity.status(HttpStatus.OK).body(paginationResponseDto);
    }

    private void mapPageToEntity(final Page<NGram> page, final PaginationResponseDto paginationResponseDto) {
        paginationResponseDto.setContent(page.getContent());
        paginationResponseDto.setPageNumber(page.getNumber());
        paginationResponseDto.setPageSize(page.getSize());
        paginationResponseDto.setTotalPages(page.getTotalPages());
        paginationResponseDto.setTotalElements(page.getNumberOfElements());
        paginationResponseDto.setLastPage(page.isLast());
    }
}