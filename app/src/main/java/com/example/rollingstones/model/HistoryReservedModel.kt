package com.example.rollingstones.model

import androidx.lifecycle.ViewModel
import kotlinx.serialization.Serializable

@Serializable
data class HistoryReservedModel (
    val date: String= "",
    val startTime: String= "",
    val endTime: String = "",
    val laneNumber: Int = 1
)