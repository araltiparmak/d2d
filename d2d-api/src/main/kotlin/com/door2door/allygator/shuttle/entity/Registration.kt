package com.door2door.allygator.shuttle.entity

import com.door2door.allygator.shuttle.enums.ActivityEnum
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document
data class Registration(@Id val id: String?,
                        val vehicleId: String,
                        val activity: ActivityEnum,
                        val createdDate: Instant) {

    companion object Factory {

        fun register(vehicleId: String) =
                Registration(null,
                        vehicleId = vehicleId,
                        activity = ActivityEnum.REGISTERED,
                        createdDate = Instant.now())

        fun unregister(vehicleId: String) =
                Registration(null,
                        vehicleId = vehicleId,
                        activity = ActivityEnum.UNREGISTERED,
                        createdDate = Instant.now())
    }
}