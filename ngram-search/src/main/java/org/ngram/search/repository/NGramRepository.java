package org.ngram.search.repository;

import org.ngram.search.constants.ApiConstant;
import org.ngram.search.entity.NGram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NGramRepository extends JpaRepository<NGram,Integer> {
    @Query(nativeQuery = true,value = ApiConstant.N_GRAM_SEARCH_QUERY)
    List<NGram> findByNgram(@Param("keyword") String keyword);
}
