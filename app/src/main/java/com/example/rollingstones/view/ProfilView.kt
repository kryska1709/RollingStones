package com.example.rollingstones.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rollingstones.components.TextFieldProfile
import com.example.rollingstones.ui.theme.BackGround
import com.example.rollingstones.ui.theme.Bibliothy
import com.example.rollingstones.ui.theme.MainColor
import com.example.rollingstones.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilView(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val isEditing = remember { mutableStateOf(false) }

    val name = remember { mutableStateOf("Иван Иванов") }
    val email = remember { mutableStateOf("ivanov@example.com") }
    val phone = remember { mutableStateOf("+7 900 123 45 67") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Ваш профиль",
            color = MainColor,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Bibliothy,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ){
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TextFieldProfile(name, "Имя", isEditing)
                TextFieldProfile(email, "Электронная почта", isEditing)
                TextFieldProfile(phone, "Телефон", isEditing)

                Button(onClick = { isEditing.value = !isEditing.value }) {
                    Text(if (isEditing.value) "Сохранить" else "Изменить")
                }
            }
        }
    }
}
