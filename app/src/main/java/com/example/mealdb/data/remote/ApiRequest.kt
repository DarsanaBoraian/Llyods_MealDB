package com.example.mealdb.data.remote


import com.example.mealdb.data.model.MealsModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API Request Interface for accessing the Meal DB
 *
 * @author darsana
 */
interface ApiRequest {

    @GET(ApiDetails.SEARCH_URL)
    suspend fun getMealsByFirstChar(@Query("f") firstChar: String): MealsModel

    //Look up individual meal details
    @GET(ApiDetails.LOOKUP_MEAL)
    suspend fun getMealDetials(@Query("i") mealId: Long): MealsModel

    @GET(ApiDetails.RANDOM_MEAL)
    suspend fun getMealOfTheDay(): MealsModel

    @GET(ApiDetails.CATGORIES)
    suspend fun getCategories(): MealsModel

    @GET(ApiDetails.LIST_ITEMS)
    suspend fun listCategories(@Query("c") category: String = "list"): MealsModel

    @GET(ApiDetails.LIST_ITEMS)
    suspend fun listCuisines(@Query("a") category: String = "list"): MealsModel

    @GET(ApiDetails.LIST_ITEMS)
    suspend fun listIngredients(@Query("i") category: String = "list"): MealsModel

    @GET(ApiDetails.FILTER_BY_ITEMS)
    suspend fun findMealsByCategory(@Query("c") category: String): MealsModel

    @GET(ApiDetails.FILTER_BY_ITEMS)
    suspend fun findMealsByCuisine(@Query("a") cuisine: String): MealsModel

    @GET(ApiDetails.FILTER_BY_ITEMS)
    suspend fun findMealsByIngredients(@Query("i") ingredient: String): MealsModel
}
