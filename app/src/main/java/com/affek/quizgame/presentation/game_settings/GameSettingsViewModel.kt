package com.affek.quizgame.presentation.game_settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GameSettingsViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(GameSettingsState())
    var state = _state.asStateFlow()



    fun onEvent(event: GameSettingsEvent) {
        when(event) {
            is GameSettingsEvent.SelectNumOfPlayers -> {
                val tempPlayerList = arrayListOf<String>()
                val playersErrorsStateTemp = arrayListOf<Boolean>()
                val playersEmptyStateTemp = arrayListOf<Boolean>()
                for (i in 1..event.number) {
                    tempPlayerList.add("Gracz$i")
                    playersErrorsStateTemp.add(false)
                    playersEmptyStateTemp.add(false)
                }
                _state.value = _state.value.copy(
                    numOfPlayers = event.number,
                    listOfPlayers = tempPlayerList.toTypedArray(),
                    playersErrorsState = playersErrorsStateTemp.toTypedArray(),
                    playersEmptyState = playersEmptyStateTemp.toTypedArray()

                )
            }
            is GameSettingsEvent.ExpandNumOfPlayersList -> {
                _state.value = _state.value.copy(
                    numOfPlayersListExpanded = !_state.value.numOfPlayersListExpanded
                )
            }
            is GameSettingsEvent.EditPlayersButton -> {
                _state.value = _state.value.copy(
                    editPlayersOpened = true,
                    tempListOfPlayers = _state.value.listOfPlayers
                )
            }
            is GameSettingsEvent.EditPlayersReject -> {
                _state.value = _state.value.copy(
                    editPlayersOpened = false,
                )
            }
            is GameSettingsEvent.EditPlayersAccept -> {
                _state.value = _state.value.copy(
                    editPlayersOpened = false,
                    listOfPlayers = _state.value.tempListOfPlayers
                )
            }
            is GameSettingsEvent.NewPlayerName -> {
                val tempPlayersErrorsState = _state.value.playersErrorsState.clone()
                val tempPlayersList = _state.value.tempListOfPlayers.clone()
                val tempPlayersEmptyState = _state.value.playersEmptyState.clone()
                tempPlayersEmptyState[event.index] = event.name.isEmpty()
                tempPlayersList[event.index] = event.name
                val seen: MutableSet<String> = mutableSetOf()
                val duplicates = tempPlayersList.filter { !seen.add(it) }.toSet()
                for ((index, value) in tempPlayersList.withIndex()) {
                    tempPlayersErrorsState[index] = value in duplicates
                }
                for ((index, value) in _state.value.tempListOfPlayers.withIndex()) {
                    if (value == event.name) {
                        tempPlayersErrorsState[event.index] = true
                        tempPlayersErrorsState[index] = true
                    }
                }
                _state.value = _state.value.copy(
                    tempListOfPlayers = tempPlayersList,
                    playersErrorsState = tempPlayersErrorsState,
                    playersEmptyState = tempPlayersEmptyState
                )
            }
            is GameSettingsEvent.ResetPlayersNames -> {
                val tempPlayerList = arrayListOf<String>()
                val playersErrorsStateTemp = arrayListOf<Boolean>()
                val playersEmptyStateTemp = arrayListOf<Boolean>()
                for (i in 1.._state.value.numOfPlayers) {
                    tempPlayerList.add("Gracz$i")
                    playersErrorsStateTemp.add(false)
                    playersEmptyStateTemp.add(false)
                }
                _state.value = _state.value.copy(
                    tempListOfPlayers = tempPlayerList.toTypedArray(),
                    playersErrorsState = playersErrorsStateTemp.toTypedArray(),
                    playersEmptyState = playersEmptyStateTemp.toTypedArray()
                )
            }
        }
    }
}