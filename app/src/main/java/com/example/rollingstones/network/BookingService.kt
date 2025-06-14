package com.example.rollingstones.network

import android.util.Log
import com.example.rollingstones.model.BookingModel
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

    suspend fun allBookings(): List<BookingModel>{
        return try{
            database.collection("Bookings")
                .orderBy("date")
                .orderBy("startTime")
                .get().await().toObjects(BookingModel::class.java)
        } catch (e: Exception){
            emptyList()
        }
    }

    suspend fun deleteBookings(
        bookingId: String
    ): Boolean{
        return try {
            database.collection("Bookings").document(bookingId).delete().await()
            true
        } catch (e: Exception){
            false
        }
    }
}