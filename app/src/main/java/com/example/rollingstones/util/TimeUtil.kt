package com.example.rollingstones.util

fun timeToMinutes(time: String): Int{
    val (hours, minutes) = time.split(":").map { it.toInt() }
    return hours*60+minutes
}