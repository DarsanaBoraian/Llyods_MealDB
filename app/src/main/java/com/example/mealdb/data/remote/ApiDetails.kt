package com.example.mealdb.data.remote

/**
 * API details with the available context to retrieve the Meal Data
 * @author darsana
 */
object ApiDetails {

    const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    // s = search criteria / f = alphabet
    const val SEARCH_URL = "search.php"

    // i = id
    const val LOOKUP_MEAL = "lookup.php"

    // Meal of the day
    const val RANDOM_MEAL =  "random.php"

    // c, a, i = list
    const val LIST_ITEMS =  "list.php"

    // c, a, i = item name
    const val FILTER_BY_ITEMS =  "filter.php"

    // CATEGORIES
    const val CATGORIES =  "categories.php"
}
