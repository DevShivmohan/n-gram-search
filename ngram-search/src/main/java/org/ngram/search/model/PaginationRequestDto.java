package org.ngram.search.model;

import lombok.Data;

@Data
public class PaginationRequestDto {
    private int pageNumber;
    private int pageSize;
    private SortOrder sortOrder;
    private String sortColumn;
}
