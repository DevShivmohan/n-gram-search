package org.ngram.search.constants;

public class ApiConstant {
    public static final String N_GRAM_SEARCH_QUERY="SELECT * , MATCH(name,bio) AGAINST(:keyword) as score FROM n_gram WHERE MATCH(name,bio) AGAINST(:keyword)  ORDER BY score DESC;";
}
