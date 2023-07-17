package com.example.mealdb.ui.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.espresso.IdlingRegistry
import com.example.mealdb.CoroutineDispatcherRule
import com.example.mealdb.navigation.Screen
import com.example.mealdb.repository.MockRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DashboardScreenTest {

    @get:Rule
    val composeRule = createComposeRule()


    @Before
    fun before() {
        composeRule.waitForIdle()
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Test
    fun displayMealList() {
        val mealsList = MockRepository.mealDetails.subList(0, 5)
        composeRule.setContent {
            DashboardContent(
                listOf(),
                mealsList, {}, {}, {}, {}
            )

        }

        composeRule.onNodeWithTag("Category").assertIsDisplayed()
        composeRule.onNodeWithTag("Ingredient").assertIsDisplayed()
        composeRule.onNodeWithTag("Cuisine").assertIsDisplayed()
        assertEquals(
            mealsList.size,
            composeRule.onAllNodesWithTag("meal_row_${Screen.Dashboard.name}")
                .fetchSemanticsNodes().size
        )
        assertEquals(
            0,
            composeRule.onAllNodesWithTag("meal_row_${Screen.Dashboard.name}mod")
                .fetchSemanticsNodes().size
        )
    }

    @Test
    fun test_mealOfDay() {
        val mealsList = MockRepository.mealDetails.subList(0, 5)
        composeRule.setContent {
            DashboardContent(
                listOf(mealsList[0]),
                listOf(), {}, {}, {}, {}
            )
        }

        assertEquals(
            0,
            composeRule.onAllNodesWithTag("meal_row_${Screen.Dashboard.name}")
                .fetchSemanticsNodes().size
        )
        assertEquals(
            1,
            composeRule.onAllNodesWithTag("meal_row_${Screen.Dashboard.name}mod")
                .fetchSemanticsNodes().size
        )
    }


    @Test
    fun test_mealOfDayWithMeals() {
        val mealsList = MockRepository.mealDetails.subList(0, 5)
        composeRule.setContent {
            DashboardContent(
                listOf(mealsList.get(0)),
                mealsList, {}, {}, {}, {}
            )
        }
        assertEquals(
            mealsList.size,
            composeRule.onAllNodesWithTag("meal_row_${Screen.Dashboard.name}")
                .fetchSemanticsNodes().size
        )
        assertEquals(
            1,
            composeRule.onAllNodesWithTag("meal_row_${Screen.Dashboard.name}mod")
                .fetchSemanticsNodes().size
        )

    }


    @Test
    fun test_searchMeals() {
        val mealsList = MockRepository.mealDetails.subList(0, 5)
        composeRule.setContent {
            DashboardContent(
                listOf(mealsList.get(0)),
                mealsList, {}, {}, {}, {}
            )
        }

        assertEquals(
            mealsList.size,
            composeRule.onAllNodesWithTag("meal_row_${Screen.Dashboard.name}")
                .fetchSemanticsNodes().size
        )
        assertEquals(
            1,
            composeRule.onAllNodesWithTag("meal_row_${Screen.Dashboard.name}mod")
                .fetchSemanticsNodes().size
        )
        val searchNode = composeRule.onNodeWithTag("search_bar_text")


        searchNode.assertIsEnabled()
        searchNode.performTextInput("b")
        searchNode.assertTextEquals("b")
        composeRule.waitUntil (20000){false}

        assertEquals(
            mealsList.filter { m -> m.strMeal?.startsWith("b") ?: false }.size,
            composeRule.onAllNodesWithTag("meal_row_${Screen.Dashboard.name}")
                .fetchSemanticsNodes().size
        )
        assertEquals(
            1,
            composeRule.onAllNodesWithTag("meal_row_${Screen.Dashboard.name}mod")
                .fetchSemanticsNodes().size
        )
    }

    @After
    fun tearDown() {

    }

}