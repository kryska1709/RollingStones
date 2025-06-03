package com.example.rollingstones.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rollingstones.model.UserModel
import com.example.rollingstones.network.AuthService
import com.example.rollingstones.network.UserService
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(): ViewModel(){
    private val authService = AuthService.instance
    private val auth = Firebase.auth
    private val userService = UserService.instance//синглтон instance

    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser = _currentUser.asStateFlow()

    private val _users = MutableStateFlow<UserModel?>(null)
    val user = _users.asStateFlow()
    init {
        getCurrentUser()
    }
    fun getCurrentUser(){
        viewModelScope.launch {
            _currentUser.value=authService.getCurrentUser()
        }
    }
    fun reg(
        email:String,
        password:String,
        onComplete: (Boolean, String?) -> Unit
    ){
        viewModelScope.launch {
            authService.registration(email, password, onComplete)
        }
    }
    fun auth(
        email:String,
        password:String,
        onComplete: (Boolean, String?) -> Unit
    ) {
        viewModelScope.launch {
            authService.authorization(email, password, onComplete)
        }
    }
    fun signOut(){
        authService.signOut()
    }
    fun deleteUser(
        email: String
    ){
        viewModelScope.launch {
            try {
                _currentUser.value?.let { authService.deleteUser(it) }
                _currentUser.value = null
                _users.value = null
                UserService.instance.deleteUser(email)
            } catch (e: Exception) {
                Log.e("deleteUser authViewModel", e.message.toString())
            }
        }
    }
    fun createUser(
        email:String,
        number:String,
        name:String
    ) {
        viewModelScope.launch {
            try {
                _users.value = userService.createUser(name, number, email)
            } catch (e: Exception){
                Log.e("createUser authViewModel",e.message.toString())
            }
        }
    }
    fun getUser(
        firebaseUser: FirebaseUser
    ){
        viewModelScope.launch {
            _users.value= userService.getUser(firebaseUser)
            Log.i("wtf",_users.value.toString())
        }
    }
}