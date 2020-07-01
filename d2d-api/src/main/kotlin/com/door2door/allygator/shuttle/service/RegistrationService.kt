package com.door2door.allygator.shuttle.service

import com.door2door.allygator.shuttle.dto.RegistrationDTO
import com.door2door.allygator.shuttle.entity.Registration
import com.door2door.allygator.shuttle.repository.RegistrationRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class RegistrationService(private val registrationRepository: RegistrationRepository) {

    fun register(registrationDTO: RegistrationDTO): Mono<Registration> {
        val registration: Registration = Registration.register(registrationDTO.id)
        return registrationRepository.save(registration)
    }

    fun unregister(registrationDTO: RegistrationDTO): Mono<Registration> {
        val registration: Registration = Registration.unregister(registrationDTO.id)
        return registrationRepository.save(registration)
    }
}