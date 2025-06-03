package com.example.rollingstones.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rollingstones.model.BookingModel
import com.example.rollingstones.model.HistoryReservedModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class BookingViewModel(): ViewModel() {
    private val database = Firebase.firestore
    private val auth = Firebase.auth

    suspend fun createBooking(
        date: String,
        time: String,
        laneNumber : Int,
        user : FirebaseUser
    ): Result<HistoryReservedModel>{

        return try {
            val historyReserve = HistoryReservedModel(date,time,laneNumber)
            val booking = BookingModel(
                userId = user.uid,
                userName = user.displayName?:"",
                userEmail = user.email?:"",
                date = date,
                time = time,
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
}