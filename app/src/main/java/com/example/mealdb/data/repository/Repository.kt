package com.example.mealdb.data.repository

import com.example.mealdb.data.model.MealsModel

/**
 * Repository API for all the External Requests
 *
 * @author darsana
 */
interface Repository {

    /**
     * Retrieve all the meals by the first char
     * @param char Char
     * @return MealsModel
     */
    suspend fun getAllMeals(char: Char): MealsModel

    /**
     * Retrieve the Meal Details by the Meal id
     * @param mealId Long
     * @return MealsModel
     */
    suspend fun getMealDetails(id: Long): MealsModel

    /**
     * Retrieve the Meal of the Day ie., Random meal in our example
     * @return MealsModel
     */
    suspend fun getMealOfTheDay(): MealsModel


    /**
     * Retrieve the List of Category Names
     * @return MealsModel with strCategory
     */
    suspend fun getCategories(): MealsModel

    /**
     * Retrieve the List of Ingredients with its Description
     * @return MealsModel with strIngredient
     */
    suspend fun getIngredients(): MealsModel

    /**
     * Retrieve the List of Cuisine Names
     * @return MealsModel with strArea
     */
    suspend fun getCuisines(): MealsModel

    /**
     * Retrieve the List of Meals by Category Name
     * @param category String
     * @return MealsModel
     */
    suspend fun findMealsByCategory(category: String): MealsModel

    /**
     * Retrieve the List of Meals by ingredient Name
     * @param ingredient String
     * @return MealsModel
     */
    suspend fun findMealsByIngredients(ingredient: String): MealsModel

    /**
     * Retrieve the List of Meals by Cuisine Name
     * @param cuisine String
     * @return MealsModel
     */
    suspend fun findMealsByCuisines(cuisine: String): MealsModel
}
