package com.example.mealdb.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealdb.data.model.MealsModel
import com.example.mealdb.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    companion object {
        const val INITIAL_CHAR = 'a'
    }

    //Stateflow has to be initialized with the default/initial value
    private val _mealsList = MutableStateFlow(MealsModel())
    val mealsList: StateFlow<MealsModel> = _mealsList

    private val _mealOfDay = MutableStateFlow( MealsModel())
    val mealOfDay: StateFlow<MealsModel> = _mealOfDay

    private var onSearchMode: Boolean = false
    private var currentChar: Char = INITIAL_CHAR

    init {
        initMeals()
        viewModelScope.launch {  _mealOfDay.value = repository.getMealOfTheDay() }
    }

    private fun initMeals() {
        retrieveMeals(currentChar, { meals -> _mealsList.value = meals })

    }

    /**
     * Returns the current char in position
     */
    fun getCurrentChar() = currentChar

    /**
     * Move to the next char and load the data. Proceed till the last char in the alphabet
     */
    fun loadMoreMeals() {
        if (!onSearchMode) {
            if (++currentChar <= 'z') {
                retrieveMeals(currentChar) { result ->
                    val newModel = (result.meals ?: ArrayList())
                    // when there is no data starting from current char, try again with next char
                    if (newModel.isEmpty()) loadMoreMeals()
                    else {
                        val current = mealsList.value
                        _mealsList.value = MealsModel(current.meals!! + newModel)
                    }
                }
            } else {
                Log.w(
                    "Dashboard",
                    "No more data left from the repository, processed till ${currentChar - 1}"
                )
            }
        }
    }

    // Search Meals
    fun searchForMeals(searchString: String) {
        if (searchString.isEmpty()) {
            exitFromSearchMode()
        } else {
            onSearchMode = true
            val searchLower = searchString.lowercase()
            retrieveMeals(searchLower[0]) { result ->
                _mealsList.value = MealsModel(result.meals?.filter { meal ->  meal.strMeal?.lowercase()?.startsWith(searchLower, true) ?: false})
            }
        }
    }

    // Common point for retrieving the meals
    private fun retrieveMeals(char: Char, callback: (MealsModel) -> Unit) {
        viewModelScope.launch {
            try {
                val result = repository.getAllMeals(char)
                callback(result)
            } catch (e: Exception) {
                Log.e("Dashboard", e.message,  e)
            }
        }
    }

    /**
     * Exits from the Search Mode. Resets to meals display
     */
    fun exitFromSearchMode() {
        if (onSearchMode) {
            onSearchMode = false
            currentChar = INITIAL_CHAR
            initMeals()
        }
    }


}
