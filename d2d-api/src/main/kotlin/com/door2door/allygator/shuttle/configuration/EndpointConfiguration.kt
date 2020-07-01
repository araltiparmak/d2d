package com.door2door.allygator.shuttle.configuration

import com.door2door.allygator.shuttle.handler.LocationHandler
import com.door2door.allygator.shuttle.handler.RegistrationHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class EndpointConfiguration {

    @Bean
    fun routes(locationHandler: LocationHandler,
               registrationHandler: RegistrationHandler) =

            router {
                POST("/vehicles/{id}/locations") { locationHandler.createLocation(it) }
                POST("/vehicles") { registrationHandler.register(it) }
                DELETE("/vehicles") { registrationHandler.unregister(it) }
            }
}
