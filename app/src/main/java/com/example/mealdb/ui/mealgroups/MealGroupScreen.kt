package com.example.mealdb.ui.mealgroups

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mealdb.data.model.MealGroup
import com.example.mealdb.data.model.MealGroupDetail
import com.example.mealdb.data.model.MealModel
import com.example.mealdb.navigation.Screen
import com.example.mealdb.ui.mealdetails.MealDetailsContent
import com.example.mealdb.ui.mealdetails.MealDetailsViewModel

@Composable
fun MealGroupSceen(
    navController: NavController,
    mealGroupStr: String
) {
    val mealGroup = MealGroup.valueOf(mealGroupStr)
    val mealGroupViewModel = hiltViewModel<MealGroupViewModel>()
    val mealGroups: List<MealGroupDetail> by mealGroupViewModel.mealGroupData.collectAsState(initial = listOf())

    mealGroupViewModel.loadMealGroupItems(mealGroup)

    val navigateToRecepie: (String, String) -> Unit = { mealGroup, name ->
        navController.navigate("${Screen.MealList.route}/$mealGroup/$name")
    }

    val scrollState = rememberScrollState()
    Surface(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {

            MealGroupList(mealGroups, navigateToRecepie)

    }
}


@Composable
fun MealGroupList(mealGroups: List<MealGroupDetail>, navigateToRecepie: (String, String) -> Unit) {

    val listState = rememberLazyListState()

    LazyColumn(state = listState, modifier = Modifier) {
        itemsIndexed(mealGroups) { index, group ->
            Column(
                modifier = Modifier
                    .clickable {
                        navigateToRecepie(group.mealGroup.name, group.name)
                    }
                    .fillMaxHeight()
                    .padding(start = 20.dp)
            ) {

                    Spacer(Modifier.height(10.dp))
                    Text(text = group.name)

            }
        }
    }
}