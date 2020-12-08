package com.springframework.recipe.converters;

import com.springframework.recipe.commands.IngredientCommand;
import com.springframework.recipe.domain.Ingredient;
import com.springframework.recipe.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if(source != null) {
            final Ingredient ingredient = new Ingredient();
            ingredient.setId(source.getId());

            if(source.getRecipeId() != null){
                Recipe recipe = new Recipe();
                recipe.setId(source.getRecipeId());
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }

            ingredient.setDescription(source.getDescription());
            ingredient.setAmount(source.getAmount());
            ingredient.setUom(uomConverter.convert(source.getUom()));
            return ingredient;
        }
        return null;
    }
}
