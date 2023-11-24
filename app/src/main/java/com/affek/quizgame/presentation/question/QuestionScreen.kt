package com.affek.quizgame.presentation.question

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.affek.quizgame.navigation.QuestionScreenNavArgs
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(
    navArgsDelegate = QuestionScreenNavArgs::class
)
fun QuestionScreen(
    navigator: DestinationsNavigator,
    viewModel: QuestionViewModel = hiltViewModel()) {

}