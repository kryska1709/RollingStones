package com.example.rollingstones.network

import android.util.Log
import com.example.rollingstones.model.HistoryReservedModel
import com.example.rollingstones.model.UserModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserService {
    companion object{val instance: UserService = UserService()}
    private val database = Firebase.firestore

    suspend fun createUser(
        name: String,
        number: String,
        email: String
    ): UserModel{
        val collection = database.collection("Users")
        val document = collection.document(email)
        val user = UserModel(name,number,email)
        document.set(user).await()
        return user
    }

    suspend fun getUser(firebaseUser: FirebaseUser): UserModel? {
        val email = firebaseUser.email ?: throw Exception("Ошибка получения почты")

        return try {
            val document = database.collection("Users").document(email).get().await()

            val historyReserveList = mutableListOf<HistoryReservedModel>()
            val historyReserveData = document.get("historyReserve") as? List<Map<String, Any>> ?: emptyList()

            historyReserveData.forEach { reservationData ->
                try {
                    val historyReserve = HistoryReservedModel(
                        date = reservationData["date"] as? String ?: "",
                        startTime = reservationData["startTime"] as? String ?: "",
                        endTime = reservationData["endTime"] as? String ?: "",
                        laneNumber = (reservationData["laneNumber"] as? Long)?.toInt() ?: 0
                    )
                    historyReserveList.add(historyReserve)
                } catch (e: Exception) {
                    Log.e("UserService", "Error parsing reservation data", e)
                }
            }

            val user = UserModel(
                name = document.getString("name") ?: "",
                number = document.getString("number") ?: "",
                email = document.getString("email") ?: "",
                historyReserve = historyReserveList,
                isAdmin = document.getBoolean("isAdmin") ?: false
            )
            user
        } catch (e: Exception) {
            Log.e("UserService", "Error fetching user", e)
            null
        }
    }

    suspend fun deleteUser(
        email: String
    ){
        database.collection("Users").document(email).delete().await()
    }
}