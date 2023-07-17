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
class MealGroupViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _itemGroupList: MutableStateFlow<List<MealGroupDetail>> = MutableStateFlow(listOf())
    val mealGroupData : StateFlow<List<MealGroupDetail>> = _itemGroupList

    fun loadMealGroupItems(mealGroup: MealGroup) {
        viewModelScope.launch {
            val res = when (mealGroup) {
                // Category will have name
                MealGroup.CATEGORY -> {
                    val res = repository.getCategories()
                    res.meals?.map { c -> MealGroupDetail(mealGroup, c.strCategory!!, c.strDescription) }
                }
                // Ingredient will have ingredient and description
                MealGroup.INGREDIENT -> {
                    val res = repository.getIngredients()
                    res.meals?.map { c -> MealGroupDetail(mealGroup, c.strIngredient!!, c.strDescription) }
                }
                // Only name of the Cusine as strArea
                MealGroup.CUISINE -> {
                    val res = repository.getCuisines()
                    res.meals?.map { c -> MealGroupDetail(mealGroup, c.strArea!!, c.strDescription) }
                }
            }
            _itemGroupList.value = res?: listOf()
        }
    }
}