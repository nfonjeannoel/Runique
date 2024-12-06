package com.camgist.run.domain

import com.camgist.core.domain.location.LocationTimestamp
import kotlin.math.roundToInt
import kotlin.text.Typography.times

object LocationDataCalculator {

    fun getTotalDistanceMeters(locations: List<List<LocationTimestamp>>): Int {
        return locations
            .sumOf { timestampsPerLine ->
                timestampsPerLine.zipWithNext { location1, location2 ->
                    location1.location.location.distanceTo(location2.location.location)
                }.sum().roundToInt()
            }
    }
}