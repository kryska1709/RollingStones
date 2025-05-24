package com.example.rollingstones.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rollingstones.ui.theme.FourthColor
import com.example.rollingstones.ui.theme.SecondColor
import com.example.rollingstones.ui.theme.ThirdColor

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
                color = Color.Blue,
                fontWeight = FontWeight.SemiBold
            )
        },
        placeholder = { Text (
            text=example,
            color = ThirdColor.copy(alpha = 1.5f),
            fontWeight = FontWeight.SemiBold
        ) },
        modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = ThirdColor,
            focusedContainerColor = Color.White,
            focusedTextColor = Color.Blue,
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Blue.copy(1.4f)
        ),
    )
}