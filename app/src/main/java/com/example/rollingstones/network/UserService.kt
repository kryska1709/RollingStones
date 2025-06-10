package com.example.rollingstones.network

import com.example.rollingstones.model.UserModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
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

    suspend fun getUser(
        currentUser: FirebaseUser
    ): UserModel?{
        val email = currentUser.email?: throw Exception("некорректный email")
        val document = database.collection("Users").document(email)
        val snapshot = document.get().await()
        if(!snapshot.exists()){
            throw Exception("все плохо")
        }
        return snapshot.toObject<UserModel>()
    }

    suspend fun deleteUser(
        email: String
    ){
        database.collection("Users").document(email).delete().await()
    }


}