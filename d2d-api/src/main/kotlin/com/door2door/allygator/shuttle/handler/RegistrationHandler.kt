package com.door2door.allygator.shuttle.handler

import com.door2door.allygator.shuttle.dto.RegistrationDTO
import com.door2door.allygator.shuttle.entity.Registration
import com.door2door.allygator.shuttle.service.RegistrationService
import org.reactivestreams.Publisher
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class RegistrationHandler(private val registrationService: RegistrationService) {

    fun register(request: ServerRequest): Mono<ServerResponse> {

        val flux = request
                .bodyToFlux(RegistrationDTO::class.java)
                .flatMap<Registration> {
                    this.registrationService.register(it)
                }

        return defaultWriteResponse(flux)
    }

    fun unregister(request: ServerRequest): Mono<ServerResponse> {

        val flux = request
                .bodyToFlux(RegistrationDTO::class.java)
                .flatMap<Registration> {
                    this.registrationService.unregister(it)
                }

        return defaultWriteResponse(flux)
    }

    private fun defaultWriteResponse(registration: Publisher<Registration>): Mono<ServerResponse> {
        return Mono
                .from(registration)
                .flatMap { noContent() }
    }

    private fun noContent() =
            ServerResponse
                    .noContent()
                    .build()
}