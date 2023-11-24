package com.affek.quizgame.presentation.game_settings

data class GameSettingsState(
    val numOfPlayers: Int = 1,
    val numOfPlayersListExpanded: Boolean = false,
    val listOfPlayers: Array<String> = arrayOf("Gracz1"),
    val playersErrorsState: Array<Boolean> = arrayOf(false),
    val playersEmptyState: Array<Boolean> = arrayOf(false),
    val tempListOfPlayers: Array<String> = emptyArray(),
    val editPlayersOpened: Boolean = false
)