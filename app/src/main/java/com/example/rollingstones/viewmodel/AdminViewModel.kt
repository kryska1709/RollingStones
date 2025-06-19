package com.example.rollingstones.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rollingstones.model.BookingModel
import com.example.rollingstones.network.BookingService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class AdminViewModel(): ViewModel() {
    private val _bookings = MutableStateFlow<List<BookingModel>>(listOf())
    val booking = _bookings.asStateFlow()
    private val _isDelete = MutableStateFlow<Boolean>(false)
    val isDelete = _isDelete.asStateFlow()

    fun allBookings(){
        viewModelScope.launch {
            _bookings.value = BookingService().allBookings()
        }
    }

    fun deleteBookings(
        bookingId: String
    ){
        viewModelScope.launch {
            _isDelete.value = BookingService().deleteBookings(bookingId)
        }
    }

    private fun isBookingActive(
        booking: BookingModel,
        currentTime: Long
    ): Boolean {
        return try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            val bookingTime = dateFormat.parse("${booking.date} ${booking.endTime}")?.time ?: 0
            bookingTime > currentTime
        } catch (e: Exception){
            false
        }
    }

    fun loadAndCleanBookings(){
        viewModelScope.launch {
            try {
                val currentBooking = BookingService().allBookings()
                val now = System.currentTimeMillis()
                val (validBookings, expiredBookings) = currentBooking.partition { isBookingActive(it, now) }
                expiredBookings.forEach { deleteBookings(it.id) }
                _bookings.value = validBookings
            } catch (e: Exception) {

            }
        }
    }
}