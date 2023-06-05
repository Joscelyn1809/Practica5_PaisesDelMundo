package com.example.practica5_paisesdelmundo.data.room.events

import com.example.practica5_paisesdelmundo.data.room.models.PositionGps
import com.example.practica5_paisesdelmundo.data.room.sorts.GpsSortType

sealed interface GpsEvent {
    object SaveGpsPoint : GpsEvent
    data class SetGpsPosition(val position: String) : GpsEvent
    data class SortGps(val sortGpsType: GpsSortType) : GpsEvent
    data class DeleteGpsPosition(val gpsPosition: PositionGps) : GpsEvent
}