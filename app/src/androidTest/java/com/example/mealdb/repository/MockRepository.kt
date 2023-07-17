package com.example.mealdb.repository

import com.example.mealdb.data.model.MealModel
import com.example.mealdb.data.model.MealsModel
import com.example.mealdb.data.repository.Repository
import com.example.mealdb.repository.MockRepository.Companion.mealDetails

import kotlin.random.Random


class MockRepository : Repository {

    companion object {
        val alphabets = ('a'..'z').toList()
        val categoryTypes =
            listOf<String>("Beef", "Chicken", "Breakfast", "Dessert", "Goat", "Lamb")
        val ingredientTypes =
            listOf<String>("Chicken", "Salmon", "Beef", "Pork", "Avocado", "Aubergine")
        val areaTypes =
            listOf<String>("Chinese", "Dutch", "Croatian", "Indian", "American", "Malaysian")
        val measureTypes = listOf<String>("30 g", " 1 kg", "200 ml")
        val mealDetails = (1..250).map { id ->
            MealModel(
                strMeal = "${alphabets[id % alphabets.size]} Meal Name $id",
                strCategory = randomPick(categoryTypes),
                strArea = randomPick(areaTypes),
                strMealThumb = "",
                strInstructions = "Test Instructions in a paragraph",
                strIngredient1 = randomPick(ingredientTypes),
                strIngredient2 = randomPick(ingredientTypes),
                strMeasure1 = randomPick(measureTypes),
                strMeasure2 = randomPick(measureTypes),
                idMeal = id.toString()
            )
        }.toList()


        private fun <T> randomPick(list: List<T>): T {
            val size = list.size
            val random = Random.nextInt(size - 1)
            return list[random]
        }
    }

    override suspend fun getAllMeals(char: Char): MealsModel {
        return MealsModel(mealDetails.filter { entry -> entry.strMeal!!.startsWith(char, true) })
    }

    override suspend fun getMealDetails(id: Long): MealsModel {
        val res = mealDetails.find { et -> et.idMeal.toString() == id.toString() }
        return res?.let { MealsModel(listOf(it)) } ?: MealsModel(listOf())
    }

    override suspend fun getMealOfTheDay(): MealsModel {
        return MealsModel(listOf(mealDetails.get(0)))
    }

    override suspend fun getCategories(): MealsModel {
        return MealsModel(categoryTypes.map { MealModel(strCategory = it) })
    }

    override suspend fun getIngredients(): MealsModel {
        return MealsModel(ingredientTypes.map { MealModel(strIngredient = it) })
    }

    override suspend fun getCuisines(): MealsModel {
        return MealsModel(areaTypes.map { MealModel(strArea = it) })
    }

    override suspend fun findMealsByCategory(category: String): MealsModel {
        return MealsModel(mealDetails.filter { it.strCategory == category })
    }

    override suspend fun findMealsByIngredients(ingredient: String): MealsModel {
        return MealsModel(mealDetails.filter {
            it.strIngredient1 == ingredient ||
                    it.strIngredient2 == ingredient
        })
    }

    override suspend fun findMealsByCuisines(cuisine: String): MealsModel {
        return MealsModel(mealDetails.filter { it.strArea == cuisine })
    }
}