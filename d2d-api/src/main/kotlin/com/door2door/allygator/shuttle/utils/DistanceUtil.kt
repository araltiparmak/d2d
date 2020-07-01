package com.door2door.allygator.shuttle.utils

import java.awt.geom.Point2D

object DistanceUtil {

    private const val DISTANCE_KM_RATIO = (3.5 / 111).toFloat()
    private val CENTER = Point2D.Float(52.53f, 13.403f)

    private fun getDistanceFromCenter(lat: Float, lng: Float): Float {
        return Point2D.Float(lat, lng)
                .distance(CENTER)
                .toFloat()
    }

    fun isInBoundaries(lat: Float, lng: Float) =
            DISTANCE_KM_RATIO > getDistanceFromCenter(lat, lng)
}