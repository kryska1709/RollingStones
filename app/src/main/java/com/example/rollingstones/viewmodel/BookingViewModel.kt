package com.example.rollingstones.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rollingstones.model.BookingModel
import com.example.rollingstones.model.HistoryReservedModel
import com.example.rollingstones.network.BookingService
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class BookingViewModel(): ViewModel() {
    private val database = Firebase.firestore
    private val auth = Firebase.auth
    private val _reserved = MutableStateFlow(false)
    val reserved = _reserved.asStateFlow()

    suspend fun createBooking(
        date: String,
        startTime: String,
        endTime: String,
        laneNumber : Int,
        userEmail: String,
        user : FirebaseUser
    ): Result<HistoryReservedModel>{

        return try {
            if(timeToMinutes(endTime)<= timeToMinutes(startTime)){
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
    private fun timeToMinutes(time: String): Int{
        val (hours, minutes) = time.split(":").map { it.toInt() }
        return hours*60+minutes
    }
    fun isTimeSlotReversed(
        date: String,
        startTime: String,
        endTime: String,
        laneNumber : Int,
        userEmail: String
    ){
        viewModelScope.launch {
            _reserved.value = BookingService().isTimeSlotReserved(date,startTime,endTime,laneNumber,userEmail)
        }
    }
}