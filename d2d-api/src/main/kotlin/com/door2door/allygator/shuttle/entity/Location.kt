package com.door2door.allygator.shuttle.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Location(
        @Id val id: String?,
        val vehicleId: String,
        val lat: Float,
        val lng: Float,
        val at: LocalDateTime)