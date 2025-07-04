package com.example.rollingstones.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.rollingstones.ui.theme.DarkButtonColor

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
                color = Color.Blue
            )
        },
        placeholder = { Text (
            text=example,
            color = DarkButtonColor.copy(alpha = 1.5f),
            fontWeight = FontWeight.SemiBold
        ) },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = DarkButtonColor,
            focusedContainerColor = Color.White,
            focusedTextColor = Color.Blue,
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Blue.copy(1.4f)
        ),
    )
}