package com.springframework.recipe.domain;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // need not specify cascading as Notes isn't the owning entity. If we delete Notes,
    // we need not delete Recipe but if we delete Recipe, we shall delete the notes
    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;
}
