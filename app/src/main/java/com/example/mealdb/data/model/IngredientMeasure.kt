package com.example.mealdb.data.model

/**
 * The Ingredient with the required measure information
 *
 * @author darsana
 */
data class IngredientMeasure(val ingredientName: String, val measure: String)

/**
 * Represents the detail of search group for mealss
 */
data class MealGroupDetail(val mealGroup: MealGroup, val name: String,
                           val description: String? = null, val thumbnail: String? = null)