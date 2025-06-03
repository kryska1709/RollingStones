package com.example.rollingstones.model

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val name: String = "",
    val number: String = "",
    val email: String = "",
    val historyReserve: List<HistoryReservedModel> = listOf()
)