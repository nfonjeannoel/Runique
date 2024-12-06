package com.camgist.run.presentation.active_run.maps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.camgist.core.domain.location.LocationTimestamp
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Polyline

@Composable
fun RuniquePolyLines(
    locations: List<List<LocationTimestamp>>
) {
    val polyLines = remember(locations) {
        locations.map {
            it.zipWithNext { timestamp1, timestamp2 ->
                PolyLineUi(
                    location1 = timestamp1.location.location,
                    location2 = timestamp2.location.location,
                    color = PolyLineColorCalculator.locationsToColor(
                        location1 = timestamp1,
                        location2 = timestamp2
                    )
                )
            }
        }
    }

    polyLines.forEach { polyline ->
        polyline.forEach { plylineUi ->
            Polyline(
                points = listOf(
                    LatLng(plylineUi.location1.lat, plylineUi.location1.long),
                    LatLng(plylineUi.location2.lat, plylineUi.location2.long)
                ),
                color = plylineUi.color,
                jointType = JointType.BEVEL
            )
        }
    }
}