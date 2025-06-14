package dev.t1r.themebuilder.ui.compose.appmenu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import composethemebuilder.composeapp.generated.resources.Res
import composethemebuilder.composeapp.generated.resources.about_title
import composethemebuilder.composeapp.generated.resources.colors_showcase_components_title
import composethemebuilder.composeapp.generated.resources.export_title
import composethemebuilder.composeapp.generated.resources.input_forms_title
import composethemebuilder.composeapp.generated.resources.theme_colors_title
import dev.t1r.themebuilder.entity.navigation.DrawerNavigationModel
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun AppMenuWidget(
    navigationModel: DrawerNavigationModel,
    onNavigationAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        TextButton(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp),
            content = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.theme_colors_title),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                    )
                )
            },
            onClick = {
                navigationModel.navigateToBaselineColors()
                onNavigationAction()
            },
        )
        TextButton(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp),
            content = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.input_forms_title),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                    )
                )
            },
            onClick = {
                navigationModel.navigateToInputForms()
                onNavigationAction()
            },
        )
        TextButton(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp),
            content = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.colors_showcase_components_title),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                    )
                )
            },
            onClick = {
                navigationModel.navigateToColorsShowcaseComponents()
                onNavigationAction()
            },
        )
        TextButton(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp),
            content = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.export_title),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                    )
                )
            },
            onClick = {
                navigationModel.navigateToExport()
                onNavigationAction()
            },
        )
        TextButton(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp),
            content = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.about_title),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                    )
                )
            },
            onClick = {
                navigationModel.navigateToAbout()
                onNavigationAction()
            },
        )
    }
}