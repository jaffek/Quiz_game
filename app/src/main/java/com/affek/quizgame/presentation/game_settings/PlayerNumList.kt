package com.affek.quizgame.presentation.game_settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerNumList(
    expandedState: Boolean,
    chosenValue: Int,
    changeExpand: () -> Unit,
    chooseNumOfPlayers: (number: Int) -> Unit,
) {
    val numOfPlayers = arrayOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)
    Box(
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            ExposedDropdownMenuBox(
                expanded = expandedState,
                onExpandedChange = {
                    changeExpand()
                }) {

                TextField(
                    value = chosenValue.toString(), onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedState)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expandedState,
                    onDismissRequest = { changeExpand() }) {
                    numOfPlayers.forEach() {
                        DropdownMenuItem(
                            text = { Text(text = it.toString()) },
                            onClick = {
                                chooseNumOfPlayers(it)
                                changeExpand()
                            })
                    }
                }
            }
        }
    }
}