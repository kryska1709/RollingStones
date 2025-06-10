package com.example.rollingstones.network

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class BookingService{
    private val database = Firebase.firestore
    suspend fun isTimeSlotReserved(
        date: String,
        startTime: String,
        endTime: String,
        laneNumber: Int
    ): Boolean{
        return try {
            val bookings = database.collection("Bookings")
                .whereEqualTo("date",date)
                .whereEqualTo("startTime",startTime)
                .whereEqualTo("endTime", endTime)
                .whereEqualTo("laneNumber", laneNumber)
                .get().await()
            bookings.isEmpty
        } catch (error: Exception){
            false
        }
    }
}