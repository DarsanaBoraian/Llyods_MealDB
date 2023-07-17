package com.example.mealdb.ui.dashboard


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mealdb.data.model.MealGroup
import com.example.mealdb.data.model.MealModel
import com.example.mealdb.data.model.MealsModel
import com.example.mealdb.navigation.Screen

@Composable
fun DashboardScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<DashboardViewModel>()
    val mealsList: MealsModel by viewModel.mealsList.collectAsState(initial = MealsModel())
    val mealsOfDay: MealsModel by viewModel.mealOfDay.collectAsState(initial = MealsModel())
    val navigateToDetails: (String) -> Unit = { mealId ->
        navController.navigate("${Screen.MealDetails.route}/$mealId")
    }

    val navigateToMealGroups: (String) -> Unit = { mealGroup ->
        navController.navigate("${Screen.MealGroup.route}/$mealGroup")
    }

    val loadMore = { viewModel.loadMoreMeals() }

    val searchFn: (String) -> Unit = { searchStr -> viewModel.searchForMeals(searchStr) }
    DashboardContent(
        mealsOfDay.meals ?: listOf(),
        mealsList.meals ?: listOf(),
        navigateToDetails,
        loadMore,
        searchFn,
        navigateToMealGroups
    )
}

@Composable
fun DashboardContent(
    mealsOfDay: List<MealModel>,
    mealsList: List<MealModel>,
    onNextClick: (String) -> Unit,
    loadMore: () -> Unit,
    searchFn: (String) -> Unit,
    navigateToMealGroups: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            // Display the meal of the day
            MealOfDay(mealsOfDay, onNextClick)
            //Spacer(modifier = Modifier.height(2.dp))
            DisplayMealItems(navigateToMealGroups)
            //Display the rest of the meals
            //Spacer(modifier = Modifier.height(2.dp))
            // Set up search state and the Search Bar
            val searchState = rememberSaveable { mutableStateOf("") }
            SearchBar(searchState)
            // call the search whenever the state value changes
            searchFn(searchState.value)
            Spacer(modifier = Modifier.height(5.dp))

            Row(modifier = Modifier) {
                ListMeals(mealsList, onNextClick, loadMore, Screen.Dashboard.name)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

    }
}

@Composable
private fun MealOfDay(
    mealsOfDay: List<MealModel>,
    onNextClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (mealsOfDay.isNotEmpty())
        mealsOfDay.get(0).idMeal?.let {
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Meal of the moment!",
                textAlign = TextAlign.Center
            )
            ListMeals(mealsList = mealsOfDay, onNextClick = onNextClick, {}, Screen.Dashboard.name + "mod")

        }
}


@Composable
private fun DisplayMealItems( navigateToGroups: (String) -> Unit, modifier: Modifier = Modifier) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.Center
    ) {
       for (item in MealGroup.values()) {
           Column(
               modifier = Modifier
                   .weight(.75f)
                   .padding(start = 10.dp, top = 10.dp, bottom = 10.dp, end = 10.dp)
                   .background(
                       color = Color(0xFF6F7FF7),
                       shape = RoundedCornerShape(16.dp)
                   ),
               verticalArrangement = Arrangement.Center
           ) {

               ClickableText(
                   AnnotatedString(item.displayName),
                   style = MaterialTheme.typography.h6.copy(color = Color.White),
                   modifier = Modifier
                       .align(Alignment.CenterHorizontally)
                       .testTag(item.displayName)
               ) {
                   navigateToGroups(item.name)
               }

           }
       }
    }
}

