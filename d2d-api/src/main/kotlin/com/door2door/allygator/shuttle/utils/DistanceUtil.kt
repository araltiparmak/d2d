package com.door2door.allygator.shuttle.utils

import java.awt.geom.Point2D

object DistanceUtil {

    private const val RADIUS_IN_KM = 3.5F
    private const val DISTANCE_BETWEEN_LATS = 111F //distance between degrees of lat/lngs is 111Km (approx.)
    private const val DISTANCE_IN_POINT = RADIUS_IN_KM / DISTANCE_BETWEEN_LATS

    private val CENTER = Point2D.Float(52.53f, 13.403f)

    private fun getDistanceFromCenterInPoint(lat: Float, lng: Float): Float {
        return Point2D.Float(lat, lng)
                .distance(CENTER)
                .toFloat()
    }

    /**
     * This method will be called for every location update.
     * No need to calculate KM every time. Instead, comparison can be done by lat/lng points
     **/
    fun isInBoundaries(lat: Float, lng: Float) =
            DISTANCE_IN_POINT > getDistanceFromCenterInPoint(lat, lng)
}