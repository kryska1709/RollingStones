package com.example.rollingstones.network

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthService{

    companion object{val instance: AuthService = AuthService()}

    private val auth = Firebase.auth
    fun registration(
        email:String,
        password:String,
        onComplete: (Boolean, String?) -> Unit
    ){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                onComplete(true, null)
            } else {
                onComplete(false, task.exception?.message)
            }
        }
    }
    fun authorization(
        email:String,
        password:String,
        onComplete: (Boolean, String?) -> Unit
    ){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                onComplete(true, null)
            } else {
                onComplete(false, task.exception?.message)
            }
        }
    }
    fun getCurrentUser() : FirebaseUser? {
        return auth.currentUser
    }
    fun signOut(){
        auth.signOut()
    }
    fun deleteUser(
        currentUser : FirebaseUser
    ){
        currentUser.delete().addOnCompleteListener{ task ->
            if(task.isSuccessful){
                Log.i("success delete user authService","user account delete")
            } else {
                Log.e("error delete user authService", task.exception?.message.toString())
            }
        }
    }
}