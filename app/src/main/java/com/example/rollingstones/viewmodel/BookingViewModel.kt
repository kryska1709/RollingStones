package com.example.rollingstones.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rollingstones.model.BookingModel
import com.example.rollingstones.model.HistoryReservedModel
import com.example.rollingstones.network.BookingService
import com.example.rollingstones.util.timeToMinutes
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class BookingViewModel: ViewModel() {

    private val database = Firebase.firestore

    private val _reserved = MutableStateFlow(false)
    val reserved = _reserved.asStateFlow()

    suspend fun createBooking(
        date: String,
        startTime: String,
        endTime: String,
        laneNumber : Int,
        user : FirebaseUser
    ): Result<HistoryReservedModel>{

        return try {
            if (timeToMinutes(endTime)<= timeToMinutes(startTime)){
                return Result.failure(Exception("endTime < startTime"))
            }
            val historyReserve = HistoryReservedModel(date,startTime, endTime, laneNumber)
            val booking = BookingModel(
                userId = user.uid,
                userName = user.displayName?:"",
                userEmail = user.email?:"",
                date = date,
                startTime = startTime,
                endTime = endTime,
                laneNumber = laneNumber,
                createdAdd = System.currentTimeMillis().toString()
            )
            val bookingRef = database.collection("Bookings").document()

            booking.id = bookingRef.id
            bookingRef.set(booking).await()
            database.collection("Users").document(user.email?:"").update("historyReserve",FieldValue.arrayUnion(historyReserve)).await()
            Result.success(historyReserve)
        } catch (error: Exception){
            Result.failure(error)
        }
    }
    fun isLaneReserved(
        date: String,
        startTime: String,
        endTime: String,
        laneNumber: Int
    ){
        viewModelScope.launch {
            _reserved.value = BookingService().isLaneReserved(date,startTime,endTime,laneNumber)
        }
    }
}