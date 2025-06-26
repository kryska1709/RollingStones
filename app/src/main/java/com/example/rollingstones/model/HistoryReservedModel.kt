package com.example.rollingstones.model

import kotlinx.serialization.Serializable

@Serializable
data class HistoryReservedModel (
    val date: String= "",
    val startTime: String= "",
    val endTime: String = "",
    val laneNumber: Int = 1,
)