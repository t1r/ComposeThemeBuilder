package dev.t1r.themebuilder.ui.compose.materialcolorspalette

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composethemebuilder.composeapp.generated.resources.Res
import composethemebuilder.composeapp.generated.resources.common_ok
import composethemebuilder.composeapp.generated.resources.msg_common_error
import dev.t1r.themebuilder.component.materialcolorspalette.MaterialColorsPaletteComponent
import dev.t1r.themebuilder.component.materialcolorspalette.MaterialColorsPaletteComponent.ContentState
import dev.t1r.themebuilder.component.materialcolorspalette.MaterialColorsPaletteComponent.Event
import dev.t1r.themebuilder.component.materialcolorspalette.MaterialColorsPaletteComponent.Model
import org.jetbrains.compose.resources.stringResource

@Composable
fun MaterialColorsPaletteContent(
    component: MaterialColorsPaletteComponent,
    modifier: Modifier = Modifier,
) {
    val model by component.models.collectAsState(Model())

    val primaryColor by animateColorAsState(Color(model.colors.primary))
    val primaryVariantColor by animateColorAsState(Color(model.colors.primaryVariant))
    val secondaryColor by animateColorAsState(Color(model.colors.secondary))
    val secondaryVariantColor by animateColorAsState(Color(model.colors.secondaryVariant))
    val backgroundColor by animateColorAsState(Color(model.colors.background))
    val surfaceColor by animateColorAsState(Color(model.colors.surface))
    val errorColor by animateColorAsState(Color(model.colors.error))

    val onPrimaryColor by animateColorAsState(Color(model.colors.onPrimary))
    val onSecondaryColor by animateColorAsState(Color(model.colors.onSecondary))
    val onBackgroundColor by animateColorAsState(Color(model.colors.onBackground))
    val onSurfaceColor by animateColorAsState(Color(model.colors.onSurface))
    val onErrorColor by animateColorAsState(Color(model.colors.onError))

    var paletteIdToDelete: Long? by remember { mutableStateOf(null) }
    var isErrorDialogShowing by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.then(
            Modifier.background(Color.White)
        ),
    ) {
        when (val contentState = model.contentState) {
            is ContentState.Normal -> MaterialColorsPaletteNormalContent(
                modifier = Modifier.fillMaxWidth(),
                primaryColor = primaryColor,
                primaryVariantColor = primaryVariantColor,
                secondaryColor = secondaryColor,
                secondaryVariantColor = secondaryVariantColor,
                backgroundColor = backgroundColor,
                surfaceColor = surfaceColor,
                errorColor = errorColor,
                onPrimaryColor = onPrimaryColor,
                onSecondaryColor = onSecondaryColor,
                onBackgroundColor = onBackgroundColor,
                onSurfaceColor = onSurfaceColor,
                onErrorColor = onErrorColor,
                isLight = model.colors.isLight,
                onThemeColorToChangeSelected = component::onThemeColorToChangeSelected,
                onChangeThemeModeClicked = component::onChangeThemeModeClicked,
                onChangePaletteClicked = component::onChangePaletteClicked,
            )

            is ContentState.ChangeColorMode -> MaterialColorsPaletteChangeColorContent(
                modifier = Modifier.fillMaxWidth(),
                contentState = contentState,
                materialColors = model.materialColors,
                onColorCandidateSelected = component::onColorCandidateSelected,
                onCancelSelectClicked = component::onCancelSelectClicked,
                onConfirmSelectedClicked = component::onConfirmSelectedClicked,
                onTextColorChanged = component::onTextColorChanged,
            )

            is ContentState.PaletteList -> MaterialColorsPaletteListContent(
                modifier = Modifier.fillMaxWidth(),
                list = model.paletteList,
                paletteIdToDelete = paletteIdToDelete,
                onBackToPaletteClicked = component::onBackToPaletteClicked,
                onAddPaletteClicked = component::onAddPaletteClicked,
                onPaletteClicked = component::onPaletteClicked,
                onDeleteClicked = { paletteIdToDelete = it },
                onCancelDeleteClicked = { paletteIdToDelete = null },
                onConfirmDeleteClicked = component::onDeleteClicked,
            )
        }
    }

    if (isErrorDialogShowing) AlertDialog(
        onDismissRequest = { isErrorDialogShowing = false },
        title = { Text(text = stringResource(Res.string.msg_common_error)) },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { isErrorDialogShowing = false }
                ) {
                    Text(stringResource(Res.string.common_ok))
                }
            }
        }
    )

    LaunchedEffect(Unit) {
        component.events.collect { event ->
            when (event) {
                is Event.Error -> {
                    isErrorDialogShowing = true
                }
            }
        }
    }
}