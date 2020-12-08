package com.springframework.recipe.repositories;

import com.springframework.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UnitOfMeasureRepositoryIT {

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    // @DirtiesContext - this will refresh the entire Spring context after this test.
    // Hence the next test will take some time to reload the entire spring context.
    void findByDescription() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription("Teaspoon");
        Optional<UnitOfMeasure> unitOfMeasure = Optional.of(uom);

        when(unitOfMeasureRepository.findByDescription("Teaspoon")).thenReturn(unitOfMeasure);

        assertEquals("Teaspoon", unitOfMeasure.get().getDescription());
    }

    @Test
    void findByDescriptionCup() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription("Cup");
        Optional<UnitOfMeasure> unitOfMeasure = Optional.of(uom);

        when(unitOfMeasureRepository.findByDescription("Cup")).thenReturn(unitOfMeasure);

        assertEquals("Cup", unitOfMeasure.get().getDescription());
    }
}