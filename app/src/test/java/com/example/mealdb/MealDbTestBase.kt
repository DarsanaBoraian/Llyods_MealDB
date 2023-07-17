package com.example.mealdb.data.repository

import com.example.mealdb.TestData
import com.example.mealdb.TestData.Companion.findMealsByCategory
import com.example.mealdb.TestData.Companion.findMealsByCuisines
import com.example.mealdb.TestData.Companion.findMealsByIngredients
import com.example.mealdb.TestData.Companion.getAllMeals
import com.example.mealdb.TestData.Companion.getCategories
import com.example.mealdb.TestData.Companion.getCuisines
import com.example.mealdb.TestData.Companion.getIngredients
import com.example.mealdb.TestData.Companion.getMealDetails
import com.example.mealdb.data.model.MealModel
import com.example.mealdb.data.model.MealsModel
import com.example.mealdb.data.remote.ApiRequest
import org.bouncycastle.asn1.x500.style.RFC4519Style.c
import org.junit.Before
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import kotlin.random.Random

abstract class MealDbTestBase {
    private val mockedApiRequest = mock<ApiRequest> ()
    val repository = RepositoryImpl(mockedApiRequest)

    @Before
    fun before() {
        mockedApiRequest.stub {
            onBlocking { getMealsByFirstChar(any()) }.thenReturn(
                getAllMeals('a'))
            onBlocking { getMealDetials(any()) }.thenReturn(getMealDetails(1))
            onBlocking { getMealOfTheDay() }.thenReturn(getMealDetails(1))
            onBlocking { listCategories() }.thenReturn(getCategories())
            onBlocking { listCuisines() }.thenReturn(getCuisines())
            onBlocking { listIngredients() }.thenReturn(getIngredients())
            onBlocking {  findMealsByCategory(eq("Vegetarian")) }.thenReturn(findMealsByCategory("Vegetarian"))
            onBlocking {  findMealsByIngredients(eq("Chicken")) }.thenReturn(findMealsByIngredients("Chicken"))
            onBlocking {  findMealsByCuisine(eq("Chinese")) }.thenReturn(findMealsByCuisines("Chinese"))
        }
    }


}