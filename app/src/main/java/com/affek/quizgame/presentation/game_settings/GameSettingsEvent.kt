package com.affek.quizgame.presentation.game_settings

sealed class GameSettingsEvent {
    data class SelectNumOfPlayers(val number: Int): GameSettingsEvent()
    object ExpandNumOfPlayersList: GameSettingsEvent()
    object EditPlayersButton: GameSettingsEvent()
    object EditPlayersReject: GameSettingsEvent()
    object EditPlayersAccept: GameSettingsEvent()
    object ResetPlayersNames: GameSettingsEvent()
    data class NewPlayerName(val index: Int, val name: String): GameSettingsEvent()
}
