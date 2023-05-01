package org.ngram.search.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "n_gram")
@Data
public class NGram {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String bio;
}
