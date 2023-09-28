package com.abi.pillsreminderabi.presentation.add_edit_Horario

import android.annotation.SuppressLint
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.abi.pillsreminderabi.domain.model.HorarioPastilla
import com.abi.pillsreminderabi.presentation.add_edit_Horario.components.TransparentHintTextField


import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditHorarioViewModel = hiltViewModel()
) {
    val titleState = viewModel.noteNombrePastilla.value
    val contentState = viewModel.noteContent.value
    val additionalTitleState = viewModel.noteDiasPastilla.value
    val additionalContentState = viewModel.noteTotalPastillasActuales.value

    val snackbarHostState = remember { SnackbarHostState() }

    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(if (noteColor != -1) noteColor else viewModel.noteColor.value)
        )
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditHorarioViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditHorarioViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditHorarioEvent.SaveNote)
                },
                containerColor	 = MaterialTheme.colorScheme.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save note")
            }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HorarioPastilla.noteColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.noteColor.value == colorInt) {
                                    Color.Blue
                                } else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    noteBackgroundAnimatable.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(
                                            durationMillis = 2000
                                        )
                                    )
                                }
                                viewModel.onEvent(AddEditHorarioEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Primera entrada de texto para el título
            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditHorarioEvent.EnteredNombrePastilla(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditHorarioEvent.ChangeNombrePastillaFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Segunda entrada de texto para el contenido
            TransparentHintTextField(
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditHorarioEvent.EnteredDescripcionPastilla(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditHorarioEvent.ChangeDescripcionPastillaFocus(it))
                },
                isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxHeight()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tercera entrada de texto para el título adicional
            TransparentHintTextField(
                text = additionalTitleState.text,
                hint = additionalTitleState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditHorarioEvent.EnteredCantidadPastillasTotales(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditHorarioEvent.ChangeCantidadPastillasTotalesFocus(it))
                },
                isHintVisible = additionalTitleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Cuarta entrada de texto para el contenido adicional
            TransparentHintTextField(
                text = additionalContentState.text,
                hint = additionalContentState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditHorarioEvent.EnteredPastillasPorDia(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditHorarioEvent.ChangePastillasPorDiaFocus(it))
                },
                isHintVisible = additionalContentState.isHintVisible,
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}
