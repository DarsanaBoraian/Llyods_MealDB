package com.example.mealdb.navigation

enum class Screen(val route: String, val header: String) {
    Dashboard("dashboard", "Recipe Galore"), MealDetails("mealdetails", "Meal Details"),
    MealGroup("groups", "Group"), MealList("meals", "Recipes")
}
