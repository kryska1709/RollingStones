package com.example.rollingstones.model

import kotlinx.serialization.Serializable

@Serializable
data class BookingModel(
    var id : String = "",
    val userId: String = "",
    val userName :String = "",
    val userEmail : String = "",
    val date : String = "",
    val startTime: String = "",
    val endTime: String = "",
    val laneNumber: Int = 1,
    val createdAdd: String = ""
)
