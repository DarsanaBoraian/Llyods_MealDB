package com.example.mealdb.data.repository

import com.example.mealdb.TestData
import com.example.mealdb.TestData.Companion.getFindByResultSize
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.sign

class RepositoryImplTest : MealDbTestBase() {

    @Test
    fun test_getAllMeals() = runBlocking  {
        val meals = repository.getAllMeals('a')
        assertEquals(  9, meals.meals?.size ?: 0)
    }

    @Test
    fun test_getMealDetails() = runBlocking  {
        val meals = repository.getMealDetails(1)
        assertEquals(  1, meals.meals?.size ?: 0)
    }

    @Test
    fun test_getMealOfTheDay() = runBlocking  {
        val meals = repository.getMealOfTheDay()
        assertEquals(  1, meals.meals?.size ?: 0)
    }

    @Test
    fun test_MealGroups() = runBlocking  {
        val categories = repository.getCategories()
        assertEquals(TestData.categoryTypes.size, categories.meals?.size?: 0)

        val ingredients = repository.getIngredients()
        assertEquals(TestData.ingredientTypes.size, ingredients.meals?.size?: 0)

        val cuisines = repository.getCuisines()
        assertEquals(TestData.areaTypes.size, cuisines.meals?.size?: 0)
    }

    @Test
    fun test_FindMeals() = runBlocking  {
        val byCategory = repository.findMealsByCategory("Vegetarian")
        assertEquals(getFindByResultSize{m -> m.strCategory == "Vegetarian"}, byCategory.meals?.size?: 0)

        val byIngredients = repository.findMealsByIngredients("Chicken")
        assertEquals(getFindByResultSize{m -> m.strIngredient2 == "Chicken" || m.strIngredient1 == "Chicken"}, byIngredients.meals?.size?: 0)

        val byCuisines = repository.findMealsByCuisines("Chinese")
        assertEquals(getFindByResultSize{m -> m.strArea == "Chinese"}, byCuisines.meals?.size?: 0)
    }
}