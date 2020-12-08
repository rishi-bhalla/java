package com.springframework.recipe.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryTest {

    Category category;

    @BeforeAll
    public void setUp() {
        category = new Category();
    }

    @Test
    public void getId() {
        Long idValue = 1L;
        category.setId(idValue);

        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}