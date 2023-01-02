package dev.t1r.themebuilder.ui.compose.materialcolorspallet

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.t1r.themebuilder.entity.colors.ThemeColorsEnum
import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent
import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent.ContentState
import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent.Model
import dev.t1r.themebuilder.ui.compose.common.getContrastColor

@Composable
fun MaterialColorsPalletContent(
    component: MaterialColorsPalletComponent,
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

    LazyColumn(
        modifier = modifier.then(
            Modifier.background(Color.White)
        ),
    ) {
        when (val contentState = model.contentState) {
            is ContentState.Normal -> {
                item(
                    key = ThemeColorsEnum.Primary.toString(),
                ) {
                    MaterialColorsPalletElementWidget(
                        modifier = Modifier.fillMaxWidth(),
                        colorTitle = ThemeColorsEnum.Primary.title,
                        onColorTitle = ThemeColorsEnum.OnPrimary.title,
                        color = primaryColor,
                        onColor = onPrimaryColor,
                        onColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.Primary) },
                        onOnColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.OnPrimary) },
                    )
                }
                item(
                    key = ThemeColorsEnum.PrimaryVariant.toString(),
                ) {
                    MaterialColorsPalletElementWidget(
                        modifier = Modifier.fillMaxWidth(),
                        colorTitle = ThemeColorsEnum.PrimaryVariant.title,
                        onColorTitle = ThemeColorsEnum.OnPrimary.title,
                        color = primaryVariantColor,
                        onColor = onPrimaryColor,
                        onColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.PrimaryVariant) },
                        onOnColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.OnPrimary) },
                    )
                }
                item(
                    key = ThemeColorsEnum.Secondary.toString(),
                ) {
                    MaterialColorsPalletElementWidget(
                        modifier = Modifier.fillMaxWidth(),
                        colorTitle = ThemeColorsEnum.Secondary.title,
                        onColorTitle = ThemeColorsEnum.OnSecondary.title,
                        color = secondaryColor,
                        onColor = onSecondaryColor,
                        onColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.Secondary) },
                        onOnColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.OnSecondary) },
                    )
                }
                item(
                    key = ThemeColorsEnum.SecondaryVariant.toString(),
                ) {
                    MaterialColorsPalletElementWidget(
                        modifier = Modifier.fillMaxWidth(),
                        colorTitle = ThemeColorsEnum.SecondaryVariant.title,
                        onColorTitle = ThemeColorsEnum.OnSecondary.title,
                        color = secondaryVariantColor,
                        onColor = onSecondaryColor,
                        onColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.SecondaryVariant) },
                        onOnColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.OnSecondary) },
                    )
                }
                item(
                    key = ThemeColorsEnum.Background.toString(),
                ) {
                    MaterialColorsPalletElementWidget(
                        modifier = Modifier.fillMaxWidth(),
                        colorTitle = ThemeColorsEnum.Background.title,
                        onColorTitle = ThemeColorsEnum.OnBackground.title,
                        color = backgroundColor,
                        onColor = onBackgroundColor,
                        onColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.Background) },
                        onOnColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.OnBackground) },
                    )
                }
                item(
                    key = ThemeColorsEnum.Surface.toString(),
                ) {
                    MaterialColorsPalletElementWidget(
                        modifier = Modifier.fillMaxWidth(),
                        colorTitle = ThemeColorsEnum.Surface.title,
                        onColorTitle = ThemeColorsEnum.OnSurface.title,
                        color = surfaceColor,
                        onColor = onSurfaceColor,
                        onColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.Surface) },
                        onOnColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.OnSurface) },
                    )
                }
                item(
                    key = ThemeColorsEnum.Error.toString(),
                ) {
                    MaterialColorsPalletElementWidget(
                        modifier = Modifier.fillMaxWidth(),
                        colorTitle = ThemeColorsEnum.Error.title,
                        onColorTitle = ThemeColorsEnum.OnError.title,
                        color = errorColor,
                        onColor = onErrorColor,
                        onColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.Error) },
                        onOnColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.OnError) },
                    )
                }
            }

            is ContentState.SelectedMode -> {
                item(
                    key = "title_key"
                ) {
                    Row(modifier = Modifier.fillMaxWidth().background(Color.Red)) {
                        Box(
                            modifier = Modifier
                                .background(Color(contentState.previousColor))
                                .weight(1F),
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                text = "New ${contentState.model.title}",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(contentState.oppositeColor),
                                )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .background(Color(contentState.newColor))
                                .weight(1F),
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                text = "Old ${contentState.model.title}",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(contentState.oppositeColor),
                                )
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier.weight(1F).padding(start = 16.dp),
                            text = "Confirm new selected ${contentState.model.title} color",
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                            )
                        )
                        TextButton(
                            modifier = Modifier.padding(end = 8.dp),
                            content = {
                                Text(
                                    text = "Cancel",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        color = Color.Black,
                                    )
                                )
                            },
                            onClick = { component.onCancelSelectClicked() },
                        )
                        OutlinedButton(
                            modifier = Modifier.padding(end = 16.dp),
                            content = {
                                Text(
                                    text = "Confirm",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        color = Color.Black,
                                    )
                                )
                            },
                            onClick = { component.onConfirmSelectedClicked() },
                        )
                    }
                    Divider(modifier = Modifier.fillMaxWidth())
                }
                items(
                    items = model.materialColors,
                    key = { it.title },
                ) { row ->
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            text = row.title,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                            )
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                        ) {
                            row.items.forEach { item ->
                                Box(
                                    modifier = Modifier
                                        .clickable(
                                            onClick = { component.onColorCandidateSelected(contentState.model, item) },
                                        )
                                        .background(Color(item.color))
                                        .padding(16.dp),
                                ) {
                                    Text(
                                        text = item.title,
                                        style = TextStyle(
                                            fontWeight = FontWeight.Normal,
                                            color = getContrastColor(Color(item.color)),
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}