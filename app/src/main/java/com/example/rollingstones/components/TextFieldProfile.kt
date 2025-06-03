package com.example.rollingstones.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.rollingstones.ui.theme.DarkButtonColor
import com.example.rollingstones.ui.theme.LightButtonColor
import com.example.rollingstones.ui.theme.MainColor

@Composable
fun TextFieldProfile(
    value: String,
    label: String,
    isEditing: State<Boolean>
) {
    val text = remember { mutableStateOf(value) }
    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text(label,
            color = MainColor) },
        enabled = isEditing.value,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = LightButtonColor.copy(0.4f),
            unfocusedTextColor = DarkButtonColor,
            focusedContainerColor = Color.White,
            focusedTextColor = Color.Blue,
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Blue.copy(1.4f),
            disabledContainerColor = Color.White, // фон при отключении
            disabledTextColor = DarkButtonColor,  // текст при отключении
            disabledIndicatorColor = Color.Blue // убрать серую линию
        )
    )
}
