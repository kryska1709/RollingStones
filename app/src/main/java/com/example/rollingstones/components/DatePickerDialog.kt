package com.example.rollingstones.components

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate

@Composable
fun DatePickerDialog(
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePicker = DatePickerDialog(context,
        { _ , selectedYear, selectedMonth, selectedDay ->
            val selectedDate = LocalDate.of(selectedYear, selectedMonth+1, selectedDay)
            val today = LocalDate.now()
            if(!selectedDate.isBefore(today)){
                onDateSelected(selectedDate)
            } else {
                Toast.makeText(context,"Bы не в прошлом", Toast.LENGTH_SHORT).show()
            }
        }, year, month, day
    )
    datePicker.datePicker.minDate = calendar.timeInMillis //получение текущей даты
    datePicker.setOnDismissListener { onDismiss() }
    datePicker.show()
}