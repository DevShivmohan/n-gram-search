package org.ngram.search.model;

import lombok.Data;
import org.ngram.search.entity.NGram;

import java.util.List;

@Data
public class PaginationResponseDto {
    private int pageNumber;
    private long pageSize;
    private int totalElements;
    private int totalPages;
    private boolean lastPage;
    private List<NGram> content;
}
