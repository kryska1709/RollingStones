package com.example.rollingstones.network

import com.example.rollingstones.model.BookingModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import kotlin.collections.sortedWith

class BookingService{
    private val database = Firebase.firestore
    suspend fun isTimeSlotReserved(
        date: String,
        startTime: String,
        endTime: String,
        laneNumber: Int,
        userEmail: String
    ): Boolean{
        return try {
            val bookings = database.collection("Bookings")
                .whereEqualTo("date",date)
                .whereEqualTo("startTime",startTime)
                .whereEqualTo("endTime", endTime)
                .whereEqualTo("laneNumber", laneNumber)
                .whereEqualTo("userEmail", userEmail)
                .get().await()
            bookings.isEmpty
        } catch (error: Exception){
            false
        }
    }

    suspend fun allBookings(): List<BookingModel>{
        return try{
            database.collection("Bookings")
                .get().await()
                .documents
                .mapNotNull { it.toObject(BookingModel::class.java)?.copy(id = it.id) }
                .sortedWith(compareBy<BookingModel>({it.date}).thenBy { it.startTime })
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