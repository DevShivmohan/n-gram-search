package org.ngram.search.controller;

import lombok.extern.slf4j.Slf4j;
import org.ngram.search.entity.NGram;
import org.ngram.search.model.PaginationRequestDto;
import org.ngram.search.model.SortOrder;
import org.ngram.search.service.NGramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

//    @GetMapping
    public ResponseEntity<?> findAllRecords(){
        return nGramService.fetchRecords();
    }

    @GetMapping
    public ResponseEntity<?> findAllRecordsWithPaginationFilterSorting(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(value = "sortOrder",defaultValue = "ASC",required = false) SortOrder sortOrder,
            @RequestParam(value = "sortColumn",defaultValue = "name",required = false) String sortColumn,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "bio",required = false) String bio,
            @RequestParam(value = "keyword",required = false) String keyword
    ){
        final PaginationRequestDto paginationRequestDto=new PaginationRequestDto();
        paginationRequestDto.setPageNumber(pageNumber);
        paginationRequestDto.setPageSize(pageSize);
        paginationRequestDto.setSortOrder(sortOrder);
        paginationRequestDto.setSortColumn(sortColumn);
        paginationRequestDto.setKeyword(keyword);

        final NGram filter=(name==null || bio==null) ? null : new NGram();
        if(filter!=null){
            filter.setName(name);
            filter.setBio(bio);
        }
        return nGramService.fetchRecordsWithPaginationFilterSorting(paginationRequestDto,filter);
    }

    /**
     * Return the base URL of the server
     * @return
     */
    @GetMapping("/base-url")
    public Object test(){
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }
}
