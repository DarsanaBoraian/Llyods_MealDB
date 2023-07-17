package com.example.mealdb.ui.mealgroups

import android.graphics.ColorSpace.match
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mealdb.data.model.MealGroup
import com.example.mealdb.data.model.MealGroupDetail
import com.example.mealdb.data.model.MealModel
import com.example.mealdb.data.model.MealsModel
import com.example.mealdb.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MealGroupRecepiesModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _mealsModel: MutableStateFlow<MealsModel> = MutableStateFlow(MealsModel())
    val mealsModelState : StateFlow<MealsModel> = _mealsModel

    fun loadRecepiesByMealGroupItem(mealGroup: MealGroup, itemName: String) {
        viewModelScope.launch {
            _mealsModel.value = when (mealGroup) {
                MealGroup.CATEGORY -> repository.findMealsByCategory(itemName)
                MealGroup.INGREDIENT -> repository.findMealsByIngredients(itemName)
                MealGroup.CUISINE -> repository.findMealsByCuisines(itemName)
            }

        }
    }
}