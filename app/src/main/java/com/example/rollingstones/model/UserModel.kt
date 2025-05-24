package com.example.rollingstones.model

data class UserModel(
    val name: String,
    val number: String,
    val email: String,
    val historyReserve: List<String> = listOf()
)