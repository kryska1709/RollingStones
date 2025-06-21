package com.example.rollingstones.view

import android.R
import android.R.style
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.rememberComposableLambdaN
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rollingstones.ui.theme.BackGround
import com.example.rollingstones.ui.theme.Bibliothy
import com.example.rollingstones.ui.theme.MainColor

@Composable
fun RulesView(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Правила для администраторов",
            fontSize = 28.sp,
            color = MainColor,
            fontWeight = FontWeight.Bold,
            fontFamily = Bibliothy,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "🎯 Общие положения",
                    fontSize = 20.sp,
                    color = MainColor,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 16.sp, fontFamily = Bibliothy)) {
                            append("1. ")
                        }
                        withStyle(style = SpanStyle(color = MainColor, fontSize = 16.sp, fontFamily = Bibliothy)) {
                            append("Администратор")
                        }
                        withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 16.sp)) {
                            append(" — лицо, ответственное за бесперебойную работу боулинг-клуба, обслуживание гостей и координацию персонала.")
                        }
                    }
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle (style = SpanStyle(color = Color.DarkGray, fontSize = 16.sp, fontFamily = Bibliothy)){
                            append("2. ")
                        }
                        withStyle(style = SpanStyle(color = MainColor, fontSize = 16.sp, fontFamily = Bibliothy)) {
                            append("Администратор")
                        }
                        withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 16.sp)) {
                            append(" обязан соблюдать вежливость, пунктуальность и внимание ко всем посетителям клуба.")
                        }
                    }
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle (style = SpanStyle(color = Color.DarkGray, fontSize = 16.sp, fontFamily = Bibliothy, fontWeight = FontWeight.Thin)){
                            append("3. ")
                        }
                        withStyle(style = SpanStyle(color = MainColor, fontSize = 16.sp, fontFamily = Bibliothy)) {
                            append("Администратор")
                        }
                        withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 16.sp)) {
                            append(" — лицо, представляющее имидж RollingStones. Внешний вид, речь и поведение должны соответствовать стандартам сервиса премиум-класса.")
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "🛠️ Обязанности администратора",
                    fontSize = 20.sp,
                    color = MainColor,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "📌 Перед открытием клуба:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainColor
                )
                Text("• Проверить готовность зала, дорожек, оборудования и кассовой системы.", fontSize = 16.sp, color = Color.DarkGray)
                Text("• Убедиться в наличии расходных материалов (шары, обувь, антисептики и т.д.).", fontSize = 16.sp, color = Color.DarkGray)
                Text("• Провести краткий инструктаж персонала по смене.", fontSize = 16.sp, color = Color.DarkGray)

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "💬 Во время работы:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainColor
                )
                Text("• Встречать гостей с улыбкой, помогать с выбором дорожки и арендой инвентаря.", fontSize = 16.sp, color = Color.DarkGray)
                Text("• Контролировать техническое состояние оборудования и реагировать на неполадки.", fontSize = 16.sp, color = Color.DarkGray)
                Text("• Следить за чистотой и порядком в зале.", fontSize = 16.sp, color = Color.DarkGray)
                Text("• Обрабатывать онлайн-бронирования и телефонные заявки.", fontSize = 16.sp, color = Color.DarkGray)
                Text("• Решать конфликтные ситуации.", fontSize = 16.sp, color = Color.DarkGray)
                Text("• Следить за соблюдением правил безопасности.", fontSize = 16.sp, color = Color.DarkGray)

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "🧾 По завершении смены:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainColor
                )
                Text("• Сдать смену по регламенту.", fontSize = 16.sp, color = Color.DarkGray)
                Text("• Подготовить отчёт о бронированиях и инцидентах.", fontSize = 16.sp, color = Color.DarkGray)
                Text("• Проверить и закрыть помещение.", fontSize = 16.sp, color = Color.DarkGray)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("👨‍💻 Работа с системой бронирования", fontSize = 20.sp, color = MainColor, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("• Проверять корректность расписания и бронирований.", fontSize = 16.sp, color = Color.DarkGray)
                Text("• Все заявки должны быть внесены и подтверждены в системе.", fontSize = 16.sp, color = Color.DarkGray)
                Text("• При накладках находить замену и информировать клиента.", fontSize = 16.sp, color = Color.DarkGray)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🧍‍♀️ Работа с персоналом
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("🧍‍♀️ Работа с персоналом", fontSize = 20.sp, color = MainColor, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("• Администратор — старший по смене, контролирует действия персонала.", fontSize = 16.sp, color = Color.DarkGray)
                Text("• При ЧП принимает решения и уведомляет руководство.", fontSize = 16.sp, color = Color.DarkGray)
                Text("• Следит за внешним видом и дисциплиной сотрудников.", fontSize = 16.sp, color = Color.DarkGray)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 📦 Оборудование
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("📦 Работа с оборудованием и инвентарём", fontSize = 20.sp, color = MainColor, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("• Проводить осмотр оборудования перед сменой.", fontSize = 16.sp, color = Color.DarkGray)
                Text("• Сообщать о поломках, оформлять заявки на ремонт.", fontSize = 16.sp, color = Color.DarkGray)
                Text("• Контролировать возврат обуви и сохранность шаров.", fontSize = 16.sp, color = Color.DarkGray)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ⚠️ Ответственность
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("⚠️ Ответственность", fontSize = 20.sp, color = MainColor, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("• За грубость и халатность — дисциплинарная ответственность.", fontSize = 16.sp, color = Color.DarkGray)
                Text("• За утрату имущества — материальная ответственность.", fontSize = 16.sp, color = Color.DarkGray)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("💡 Принципы RollingStones", fontSize = 20.sp, color = MainColor, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("• Гость всегда прав.", fontSize = 16.sp, color = Color.DarkGray)
                Text("• Каждый визит — праздник.", fontSize = 16.sp, color = Color.DarkGray)
                Text("• Быстро, вежливо, профессионально.", fontSize = 16.sp, color = Color.DarkGray)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}
