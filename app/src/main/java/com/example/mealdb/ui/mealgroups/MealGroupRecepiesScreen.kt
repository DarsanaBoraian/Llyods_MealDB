package com.example.mealdb.ui.mealgroups

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mealdb.data.model.MealGroup
import com.example.mealdb.data.model.MealsModel
import com.example.mealdb.navigation.Screen
import com.example.mealdb.ui.dashboard.ListMeals

@Composable
fun MealGroupRecepiesScreen(navController: NavController,
                            mealGroupStr: String, mealGroupItem: String) {
    val viewModel = hiltViewModel<MealGroupRecepiesModel>()
    val mealsList: MealsModel by viewModel.mealsModelState.collectAsState(initial = MealsModel())
    viewModel.loadRecepiesByMealGroupItem(MealGroup.valueOf(mealGroupStr), mealGroupItem)

    val navigateToDetails: (String) -> Unit = { mealId ->
        navController.navigate("${Screen.MealDetails.route}/$mealId")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

            ListMeals(mealsList.meals?: listOf(), navigateToDetails, null, Screen.MealList.name)

    }
}