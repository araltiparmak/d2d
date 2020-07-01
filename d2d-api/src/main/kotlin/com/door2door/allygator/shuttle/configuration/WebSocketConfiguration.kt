package com.door2door.allygator.shuttle.configuration

import com.door2door.allygator.shuttle.entity.Location
import com.door2door.allygator.shuttle.event.LocationUpdatedEventPublisher
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter
import reactor.core.publisher.Flux
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Configuration
class WebSocketConfiguration(private val objectMapper: ObjectMapper) {

    @Bean
    fun handlerMapping(wsh: WebSocketHandler) = getSimpleUrlHandlerMapping(wsh)

    @Bean
    fun getSimpleUrlHandlerMapping(wsh: WebSocketHandler): SimpleUrlHandlerMapping {
        return SimpleUrlHandlerMapping().apply {
            urlMap = Collections.singletonMap("/ws/locations", wsh)
            order = 10
        }
    }

    @Bean
    fun webSocketHandlerAdapter() = WebSocketHandlerAdapter()

    @Bean
    fun executor(): ExecutorService = Executors.newSingleThreadExecutor()

    @Bean
    fun webSocketHandler(eventPublisher: LocationUpdatedEventPublisher): WebSocketHandler {

        val publish = Flux.create(eventPublisher).share()
        return WebSocketHandler { session ->

            val messageFlux = publish
                    .map { it.source as Location }
                    .map { writeValue(it) }
                    .map { str -> sendSocketMessage(str, session) }
            session.send(messageFlux)
        }
    }

    private fun sendSocketMessage(str: String, session: WebSocketSession): WebSocketMessage {
        println("sending $str")
        return session.textMessage(str)
    }

    private fun writeValue(location: Location): String {
        try {
            return objectMapper.writeValueAsString(map(location))

        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }
    }

    private fun map(location: Location): Map<String, Any> {
        return mapOf(
                "vehicleId" to location.vehicleId,
                "at" to location.at,
                "lat" to location.lat,
                "lng" to location.lng)
    }
}