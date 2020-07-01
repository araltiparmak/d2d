package com.door2door.allygator.shuttle.dto

import java.time.LocalDateTime

data class LocationDTO(val lat: Float,
                       val lng: Float,
                       val at: LocalDateTime)