package com.affek.quizgame.presentation.game_settings

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.affek.quizgame.R
import com.affek.quizgame.navigation.QuestionScreenNavArgs
import com.affek.quizgame.presentation.destinations.GameSettingsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun GameSettingsScreen(
    navigator: DestinationsNavigator,
    viewModel: GameSettingsViewModel = hiltViewModel()
) {

    val state: GameSettingsState by viewModel.state.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.game_settings),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = stringResource(id = R.string.num_players),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp
            )

                PlayerNumList(
                    expandedState = state.numOfPlayersListExpanded,
                    chosenValue = state.numOfPlayers,
                    changeExpand = { viewModel.onEvent(GameSettingsEvent.ExpandNumOfPlayersList) },
                    chooseNumOfPlayers = { viewModel.onEvent(GameSettingsEvent.SelectNumOfPlayers(it)) })

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.3f)
                    .align(Alignment.CenterHorizontally)
                    .border(BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),shape = RoundedCornerShape(5.dp)),

                ) {
                Column(
                    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()).padding(start = 5.dp, end = 5.dp)
                ) {
                    state.listOfPlayers.forEachIndexed() { index, player ->
                        Text(
                            text = "${player}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Divider(color = MaterialTheme.colorScheme.onSecondary, thickness = 1.dp)
                    }
                }

            }

            Button(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = { viewModel.onEvent(GameSettingsEvent.EditPlayersButton)

                    },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                border = BorderStroke(
                    color = MaterialTheme.colorScheme.primary,
                    width = dimensionResource(id = R.dimen.menu_buttons_border)
                )
            ) {
                Text(text = stringResource(id = R.string.edit_players))
            }
            Divider(color = MaterialTheme.colorScheme.onSecondary, thickness = 1.dp)
            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = stringResource(id = R.string.number_of_questions),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp
            )

            PlayerNumList(
                expandedState = state.numOfQuestionsExpanded,
                chosenValue = state.numOfQuestions,
                changeExpand = { viewModel.onEvent(GameSettingsEvent.ExpandNumOfQuestionsList) },
                chooseNumOfPlayers = { viewModel.onEvent(GameSettingsEvent.SelectNumOfQuestions(it)) })

            Spacer(
                modifier = Modifier.weight(1f)
            )
            Button(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = { navigator.navigate(
                    com.affek.quizgame.presentation.destinations.QuestionScreenDestination(
                        QuestionScreenNavArgs(listOfPlayers = state.listOfPlayers, numOfQuestions = state.numOfQuestions)
                    )
                )
                     },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                border = BorderStroke(
                    color = MaterialTheme.colorScheme.primary,
                    width = dimensionResource(id = R.dimen.menu_buttons_border)
                )
            ) {
                Text(
                    text = stringResource(id = R.string.start_game),
                    fontSize = 20.sp
                    )
            }


        }
        if (state.editPlayersOpened) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        navigationIcon = {
                            Row(
                                horizontalArrangement = Arrangement.Center
                            ) {
                                FilledIconButton(
                                    onClick = { viewModel.onEvent(GameSettingsEvent.EditPlayersReject) },
                                    colors = IconButtonDefaults.filledIconButtonColors(
                                        containerColor = Color(0xFFD30000),
                                        contentColor = MaterialTheme.colorScheme.surface
                                    ),

                                    ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_clear_24),
                                        contentDescription = stringResource(id = R.string.decline),
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                Button(
                                    onClick = { viewModel.onEvent(GameSettingsEvent.ResetPlayersNames) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent,
                                        contentColor = MaterialTheme.colorScheme.primary
                                    ),
                                    border = BorderStroke(
                                        color = MaterialTheme.colorScheme.primary,
                                        width = dimensionResource(id = R.dimen.menu_buttons_border_small)
                                    )
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.reset_players_name),
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                FilledIconButton(
                                    onClick = { viewModel.onEvent(GameSettingsEvent.EditPlayersAccept) },
                                    colors = IconButtonDefaults.filledIconButtonColors(
                                        containerColor = Color(0xFF02911A),
                                        contentColor = MaterialTheme.colorScheme.surface

                                    ),
                                    enabled = !(true in state.playersErrorsState || true in state.playersEmptyState)
                                ) {

                                    Icon(
                                        modifier = Modifier.padding(10.dp),
                                        painter = painterResource(id = R.drawable.baseline_check_24),
                                        contentDescription = stringResource(id = R.string.accept),
                                    )

                                }
                            }
                        },
                        title = {},
                        actions = { },
                        colors = TopAppBarDefaults.mediumTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            scrolledContainerColor = MaterialTheme.colorScheme.surface,
                            navigationIconContentColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                            actionIconContentColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .statusBarsPadding()
                    )
                },
                modifier = Modifier
                    .fillMaxSize()
            )
            {
                BackHandler() {
                    viewModel.onEvent(GameSettingsEvent.EditPlayersReject)
                }
                Column(
                    modifier = Modifier.padding(it).verticalScroll(rememberScrollState())
                ) {
                    state.listOfPlayers.forEachIndexed() { index, _ ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "${index + 1}.",
                                modifier = Modifier.align(Alignment.CenterVertically)
                                    .padding(end = 10.dp)
                                    .width(30.dp)
                            )
                            TextField(
                                value = state.tempListOfPlayers[index],
                                onValueChange = { newName ->
                                    if (newName.trim().take(15) != state.tempListOfPlayers[index]) {
                                        viewModel.onEvent(GameSettingsEvent.NewPlayerName(index, newName.trim().take(15)))
                                    }
                                                },
                                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                                isError = state.playersErrorsState[index] || state.playersEmptyState[index],
                                supportingText = {
                                    if (state.playersEmptyState[index]) {
                                        Text(
                                            modifier = Modifier.fillMaxWidth(),
                                            text = "Pole nie może być puste",
                                            color = MaterialTheme.colorScheme.error
                                        )
                                    }
                                    else if (state.playersErrorsState[index]) {
                                        Text(
                                            modifier = Modifier.fillMaxWidth(),
                                            text = "Nazwy graczy muszą się różnić",
                                            color = MaterialTheme.colorScheme.error
                                        )
                                    }
                                },
                                singleLine = true
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))

                    }
                }
            }
        }
    }
}