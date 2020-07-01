package com.door2door.allygator.shuttle.repository

import com.door2door.allygator.shuttle.entity.Registration
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RegistrationRepository : ReactiveMongoRepository<Registration, String>