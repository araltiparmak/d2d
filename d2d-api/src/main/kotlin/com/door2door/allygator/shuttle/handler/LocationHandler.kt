package com.door2door.allygator.shuttle.handler

import com.door2door.allygator.shuttle.dto.LocationDTO
import com.door2door.allygator.shuttle.entity.Location
import com.door2door.allygator.shuttle.service.LocationUpdateService
import com.door2door.allygator.shuttle.utils.DistanceUtil
import org.reactivestreams.Publisher
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class LocationHandler(private val locationUpdateService: LocationUpdateService) {

    fun createLocation(request: ServerRequest): Mono<ServerResponse> {

        val flux: Flux<Location> = request
                .bodyToFlux(LocationDTO::class.java)
                .filter { DistanceUtil.isInBoundaries(it.lat, it.lng) }
                .flatMap<Location> { toWrite -> this.locationUpdateService.createLocation(toWrite, getId(request)) }

        return defaultWriteResponse(flux)
    }

    private fun getId(r: ServerRequest): String {
        return r.pathVariable("id")
    }

    //todo
    private fun defaultWriteResponse(location: Publisher<Location>): Mono<ServerResponse> {

        return Mono
                .from(location)
                .flatMap {
                    ServerResponse
                            .noContent()
                            .build()
                }
    }
}