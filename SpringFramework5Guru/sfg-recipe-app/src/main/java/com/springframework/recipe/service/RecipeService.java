package com.springframework.recipe.service;

import com.springframework.recipe.commands.RecipeCommand;
import com.springframework.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    void deleteById(Long l);

    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
