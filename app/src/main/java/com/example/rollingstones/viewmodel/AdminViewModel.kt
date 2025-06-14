package com.example.rollingstones.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rollingstones.model.BookingModel
import com.example.rollingstones.network.BookingService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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
}