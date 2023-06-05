package com.example.practica5_paisesdelmundo.data.room.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica5_paisesdelmundo.data.room.GpsState
import com.example.practica5_paisesdelmundo.data.room.daos.GpsDao
import com.example.practica5_paisesdelmundo.data.room.events.GpsEvent
import com.example.practica5_paisesdelmundo.data.room.models.PositionGps
import com.example.practica5_paisesdelmundo.data.room.sorts.GpsSortType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GpsViewModel(private val gpsDao: GpsDao) : ViewModel() {
    private val _sortType = MutableStateFlow(GpsSortType.GPS_POSITION)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _positions = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                GpsSortType.GPS_POSITION -> gpsDao.getAllPositions()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(GpsState())

    val gpsState = combine(_state, _sortType, _positions) { state, sortType, positions ->
        state.copy(
            positions = positions
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), GpsState())

    fun onEvent(event: GpsEvent) {
        when (event) {
            is GpsEvent.DeleteGpsPosition -> {
                viewModelScope.launch {
                    gpsDao.deleteGps(event.gpsPosition)
                }
            }

            GpsEvent.SaveGpsPoint -> {
                val gpsPosition = gpsState.value.position

                if (gpsPosition.isBlank()) {
                    return
                }
                val position = PositionGps(
                    position = gpsPosition,
                    pointIdFk = 1
                )
                viewModelScope.launch {
                    gpsDao.upsertGps(position)
                    _state.update {
                        it.copy(
                            position = ""
                        )
                    }
                }
            }

            is GpsEvent.SetGpsPosition -> {
                _state.update {
                    it.copy(
                        position = event.position
                    )
                }
            }

            is GpsEvent.SortGps -> {
                _sortType.value = event.sortGpsType
            }
        }
    }
}