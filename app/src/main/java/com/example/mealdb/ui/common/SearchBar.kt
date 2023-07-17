package com.example.mealdb.ui.dashboard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(state: MutableState<String>,
               modifier: Modifier =  Modifier.fillMaxWidth().height(60.dp)
                   .padding(start = 20.dp, end = 20.dp)) {

    TextField(
        modifier = modifier.fillMaxWidth().testTag("search_bar_text"),
        value = state.value,
        onValueChange = { value  ->
            state.value = value
        },
        textStyle = TextStyle(color = Color(0xFF6F7FF7), fontSize = 18.sp),
        placeholder = {
            Text(text = "Search", color = Color(0xFF6F7FF7), fontSize = 18.sp)
        },
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF6F7FF7),
            cursorColor = Color(0xFF6F7FF7),
            leadingIconColor = Color(0xFF6F7FF7),
            trailingIconColor = Color(0xFF6F7FF7),
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),

        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != "") {
                IconButton(
                    onClick = {
                        state.value = "" // Remove text from TextField when you press the 'X' icon
                    },
                    modifier = Modifier
                        .size(24.dp)
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            }
        }

    )
}

