package com.example.rollingstones.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.rollingstones.R
import com.example.rollingstones.ui.theme.MainColor
import com.example.rollingstones.ui.theme.DarkButtonColor
import com.example.rollingstones.ui.theme.SecondColor

@Composable
fun PasswordView(
    password: MutableState<String>,
    labelName: String,
    example: String
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    TextField(
        value = password.value,
        onValueChange = { password.value = it },
        label = {
            Text(
                text = labelName,
                color = Color.Blue,
            )
        },
        placeholder = {
            Text(
                text = example,
                color = DarkButtonColor.copy(alpha = 1.5f),
                fontWeight = FontWeight.Bold
            )
        },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            IconButton(
                modifier = Modifier.size(25.dp),
                onClick = { isPasswordVisible = !isPasswordVisible }
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isPasswordVisible)
                            R.drawable.eye_not_visible
                        else
                            R.drawable.eye_visible
                    ),
                    contentDescription = null,
                    tint = SecondColor
                )
            }
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = DarkButtonColor,
            focusedContainerColor = Color.White,
            focusedTextColor = Color.Blue,
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Blue.copy(1.4f)
        ),
        visualTransformation = if (isPasswordVisible)
            VisualTransformation.None
        else
            PasswordVisualTransformation()
    )
}
