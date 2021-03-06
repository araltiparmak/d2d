package com.door2door.allygator.shuttle.repository

import com.door2door.allygator.shuttle.entity.Location
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationRepository : ReactiveMongoRepository<Location, String>