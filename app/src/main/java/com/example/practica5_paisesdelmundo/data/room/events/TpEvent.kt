package com.example.practica5_paisesdelmundo.data.room.events

import com.example.practica5_paisesdelmundo.data.room.models.TouristicPoint
import com.example.practica5_paisesdelmundo.data.room.sorts.TpSortType

sealed interface TpEvent {
    object SaveTouristicPoint : TpEvent
    data class SetTpName(val tpName: String) : TpEvent
    data class SetTpDescription(val tpDescription: String) : TpEvent
    data class SetTpFee(val tpFee: String) : TpEvent
    data class SortTp(val sortTpType: TpSortType) : TpEvent
    data class DeleteTouristicPoint(val touristicPoint: TouristicPoint) : TpEvent
}