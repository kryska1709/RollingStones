package com.example.rollingstones.network
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthService {
    private val auth = Firebase.auth
    fun registration(
        email:String,
        password:String,
        onSuccess: () -> Unit,
        onError : (Exception) -> Unit
    ){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                onSuccess.invoke()
            } else {
                task.exception?.let { onError.invoke(it) }
            }
        }
    }
    fun authorization(email:String,
                      password:String,
                      onSuccess: () -> Unit,
                      onError : (Exception) -> Unit
    ){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                onSuccess.invoke()
            } else {
                task.exception?.let { onError.invoke(it) }
            }
        }
    }
}