package com.example.rollingstones.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import com.example.rollingstones.ui.theme.DarkButtonColor
@Composable
fun TimeTextField(
    text: String,
    onValueChanged: (String) -> Unit
) {
    val value = remember { mutableStateOf(text) }

    TextField(
        value = value.value,
        onValueChange = { newText ->
            // Оставляем только цифры и ограничиваем до 4 символов (ЧЧММ)
            val digits = newText.filter { it.isDigit() }.take(4)

            // Форматируем в ЧЧ:ММ
            val formatted = buildString {
                for (i in digits.indices) {
                    append(digits[i])
                    if (i == 1 && digits.length > 2) append(':')
                }
            }

            value.value = formatted
            onValueChanged(formatted)
        },
        label = {
            Text(
                text = "Время",
                color = Color.Blue
            )
        },
        placeholder = {
            Text(
                text = "19:00",
                color = DarkButtonColor.copy(alpha = 0.7f),
                fontWeight = FontWeight.SemiBold
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = DarkButtonColor,
            focusedContainerColor = Color.White,
            focusedTextColor = Color.Blue,
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Blue.copy(alpha = 1.4f)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}
