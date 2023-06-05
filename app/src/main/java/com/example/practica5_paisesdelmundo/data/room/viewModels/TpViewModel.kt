package com.example.practica5_paisesdelmundo.data.room.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica5_paisesdelmundo.data.room.TPState
import com.example.practica5_paisesdelmundo.data.room.daos.TouristicPointDao
import com.example.practica5_paisesdelmundo.data.room.events.TpEvent
import com.example.practica5_paisesdelmundo.data.room.models.TouristicPoint
import com.example.practica5_paisesdelmundo.data.room.sorts.TpSortType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TpViewModel(private val tpDao: TouristicPointDao) : ViewModel() {
    private val _sortType = MutableStateFlow(TpSortType.TOURISTIC_NAME)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _points = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                TpSortType.TOURISTIC_NAME -> tpDao.getAllPointsOrderedByName()
                TpSortType.FEE -> tpDao.getAllPointsOrderedByFee()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(TPState())

    val tpState = combine(_state, _sortType, _points) { state, sortType, points ->
        state.copy(
            points = points,
            tpSortType = sortType

        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TPState())

    fun onEvent(event: TpEvent) {
        when (event) {
            is TpEvent.DeleteTouristicPoint -> {
                viewModelScope.launch {
                    tpDao.deleteTp(event.touristicPoint)
                }
            }

            TpEvent.SaveTouristicPoint -> {
                val tpName = tpState.value.touristicName
                val tpDescription = tpState.value.description
                val tpFee = tpState.value.fee

                if (tpName.isBlank() || tpDescription.isBlank() || tpFee.isBlank()) {
                    return
                }
                val point = TouristicPoint(
                    touristicName = tpName,
                    touristicDescription = tpDescription,
                    fee = tpFee,
                    cityIdFk = 1
                )
                viewModelScope.launch {
                    tpDao.upsertTp(point)
                    _state.update {
                        it.copy(
                            touristicName = "",
                            description = "",
                            fee = ""
                        )
                    }
                }
            }

            is TpEvent.SetTpName -> {
                _state.update {
                    it.copy(
                        touristicName = event.tpName
                    )
                }
            }

            is TpEvent.SetTpDescription -> {
                _state.update {
                    it.copy(
                        description = event.tpDescription
                    )
                }
            }

            is TpEvent.SetTpFee -> {
                _state.update {
                    it.copy(
                        fee = event.tpFee
                    )
                }
            }

            is TpEvent.SortTp -> {
                _sortType.value = event.sortTpType
            }
        }
    }
}