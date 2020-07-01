package com.door2door.allygator.shuttle.service

import com.door2door.allygator.shuttle.dto.LocationDTO
import com.door2door.allygator.shuttle.entity.Location
import com.door2door.allygator.shuttle.event.LocationUpdatedEvent
import com.door2door.allygator.shuttle.repository.LocationRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class LocationUpdateService(private val publisher: ApplicationEventPublisher,
                            private val locationRepository: LocationRepository) {

    fun createLocation(locationDto: LocationDTO, vehicleId: String): Mono<Location> {
        val locationHistory = Location(
                null,
                vehicleId,
                locationDto.lat,
                locationDto.lng,
                locationDto.at)

        this.publisher.publishEvent(LocationUpdatedEvent(locationHistory))
        return this.locationRepository.save(locationHistory)
    }
}