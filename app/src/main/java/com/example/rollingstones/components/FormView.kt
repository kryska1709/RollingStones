package com.example.rollingstones.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rollingstones.ui.theme.BluDark
import com.example.rollingstones.ui.theme.Blues
import com.example.rollingstones.ui.theme.DarkPurple
import com.example.rollingstones.ui.theme.MyPurple
import com.example.rollingstones.ui.theme.StrangeViolet

@Composable
fun FormView(
    text: MutableState<String>,
    labelName : String,
    example : String
){
    TextField(
        value = text.value,
        onValueChange = {text.value = it},
        label = {
            Text(
                text = labelName,
                color = StrangeViolet,
                fontWeight = FontWeight.SemiBold
            )
        },
        placeholder = { Text (
            text=example,
            color = Color.Blue.copy(0.8f)
        ) },
        modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = Color.Blue,
            focusedContainerColor = Color.White,
            focusedTextColor = BluDark,
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Blue.copy(1.4f)
        ),
    )
}