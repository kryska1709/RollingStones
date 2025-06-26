package com.example.rollingstones.network

import com.example.rollingstones.model.BookingModel
import com.example.rollingstones.util.timeToMinutes
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import kotlin.collections.sortedWith

class BookingService{
    private val database = Firebase.firestore

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

    suspend fun isLaneReserved(
        date: String,
        startTime: String,
        endTime: String,
        laneNumber: Int
    ) : Boolean{
        return try{
            val bookings = database.collection("Bookings")
                .whereEqualTo("date", date)
                .whereEqualTo("laneNumber", laneNumber)
                .get().await().documents.mapNotNull { it.toObject(BookingModel::class.java) }
            bookings.none{
                val bookingStart = timeToMinutes(it.startTime)
                val bookingEnd = timeToMinutes(it.endTime)
                val newStart = timeToMinutes(startTime)
                val newEnd = timeToMinutes(endTime)
                newStart < bookingEnd && newEnd > bookingStart
            }
        } catch (e: Exception){
            false
        }
    }
}