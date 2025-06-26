package com.example.rollingstones.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import com.example.rollingstones.naviigation.Screen
import com.example.rollingstones.ui.theme.DarkButtonColor

@Composable
fun CustomTextButton(
    navController: NavController
){
    val annotatedString = buildAnnotatedString {
        append("Нет аккаунта?")
        withAnnotation("reg","registration"){ withStyle(style = SpanStyle(DarkButtonColor)){append(" зарегистрироваться")}}
    }
    TextButton(
        onClick = {
            annotatedString.getStringAnnotations("reg",0,1).firstOrNull()?.let{
                if(it.item == "registration"){navController.navigate(Screen.RegScreen.route)}
            }
        }
    ){
        Text(
            text = annotatedString
        )
    }
}