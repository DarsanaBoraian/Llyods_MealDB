package com.example.mealdb.data.repository

import com.example.mealdb.data.model.MealsModel
import com.example.mealdb.data.remote.ApiRequest
import javax.inject.Inject

/**
 * Repository Implementation for all the API Requests
 * @link Repository
 * @author darsana
 */
class RepositoryImpl @Inject constructor(
    private val apiRequest: ApiRequest
) : Repository {

    /**
     * Retrieve all the meals by the first char
     * @param char Char
     * @return MealsModel
     */
    override suspend fun getAllMeals(char: Char): MealsModel {
        return apiRequest.getMealsByFirstChar(char.toString())
    }

    /**
     * Retrieve the Meal Details by the Meal id
     * @param mealId Long
     * @return MealsModel
     */
    override suspend fun getMealDetails(mealId: Long): MealsModel {
        return apiRequest.getMealDetials(mealId)
    }

    /**
     * Retrieve the Meal of the Day ie., Random meal in our example
     * @return MealsModel
     */
    override suspend fun getMealOfTheDay(): MealsModel {
        return apiRequest.getMealOfTheDay()
    }


    /**
     * Retrieve the List of Category Names
     * @return MealsModel with strCategory
     */
    override suspend fun getCategories(): MealsModel {
        return apiRequest.listCategories()
    }

    /**
     * Retrieve the List of Ingredients with its Description
     * @return MealsModel with strIngredient
     */
    override suspend fun getIngredients(): MealsModel {
        return apiRequest.listIngredients()
    }

    /**
     * Retrieve the List of Cuisine Names
     * @return MealsModel with strArea
     */
    override suspend fun getCuisines(): MealsModel {
        return apiRequest.listCuisines()
    }

    /**
     * Retrieve the List of Meals by Category Name
     * @param category String
     * @return MealsModel
     */
    override suspend fun findMealsByCategory(category: String): MealsModel {
        return apiRequest.findMealsByCategory(category)
    }

    /**
     * Retrieve the List of Meals by Ingredient Name
     * @param ingredient String
     * @return MealsModel
     */
    override suspend fun findMealsByIngredients(ingredient: String): MealsModel {
        return apiRequest.findMealsByIngredients(ingredient)
    }

    /**
     * Retrieve the List of Meals by Cuisine Name
     * @param cuisine String
     * @return MealsModel
     */
    override suspend fun findMealsByCuisines(cuisine: String): MealsModel {
        return apiRequest.findMealsByCuisine(cuisine)
    }
}
