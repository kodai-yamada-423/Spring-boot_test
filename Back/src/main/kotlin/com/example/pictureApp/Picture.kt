package com.example.pictureApp

import java.time.LocalDateTime

data class Picture(
    val id: Long = 0,
    val image: ByteArray,
    val title: String,
    val create_time: LocalDateTime = LocalDateTime.now(),
    val user_id: Long
)