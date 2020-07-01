package com.door2door.allygator.shuttle.handler

import com.door2door.allygator.shuttle.dto.LocationDTO
import com.door2door.allygator.shuttle.entity.Location
import com.door2door.allygator.shuttle.service.LocationUpdateService
import com.door2door.allygator.shuttle.utils.DistanceUtil
import org.reactivestreams.Publisher
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class LocationHandler(private val locationUpdateService: LocationUpdateService) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(LocationHandler::class.java)
    }

    fun createLocation(request: ServerRequest): Mono<ServerResponse> {

        val flux: Flux<Location> = request
                .bodyToFlux(LocationDTO::class.java)
                .filter { inBoundaries(it) }
                .flatMap<Location> { toWrite -> this.locationUpdateService.createLocation(toWrite, getId(request)) }
        return defaultWriteResponse(flux)
    }


    private fun inBoundaries(locationDto: LocationDTO): Boolean {

        val isInBoundaries = DistanceUtil.isInBoundaries(locationDto.lat, locationDto.lng)

        if (!isInBoundaries) {
            logger.info("Ignoring Location Update: $locationDto")
        }

        return isInBoundaries
    }

    private fun getId(r: ServerRequest) = r.pathVariable("id")

    private fun defaultWriteResponse(location: Publisher<Location>): Mono<ServerResponse> {
        return Mono
                .from(location)
                .flatMap { noContent() }
    }

    private fun noContent() =
            ServerResponse
                    .noContent()
                    .build()
}