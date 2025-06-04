package dev.t1r.themebuilder.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import composethemebuilder.composeapp.generated.resources.Res
import composethemebuilder.composeapp.generated.resources.*
import dev.t1r.themebuilder.entity.navigation.DrawerNavigationModel
import dev.t1r.themebuilder.ui.compose.common.ScreenContainerWidget
import org.jetbrains.compose.resources.stringResource

const val URL_PROJECT = "https://github.com/t1r/ComposeThemeBuilder"

@Composable
fun AboutContent(
    navigationModel: DrawerNavigationModel,
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current
    ScreenContainerWidget(
        navigationModel = navigationModel,
        title = stringResource(Res.string.about_title),
        content = { pv ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(pv)
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .fillMaxWidth(),
                    text = stringResource(Res.string.about_used_dependencies),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        color = Color.Black,
                    ),
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .fillMaxWidth(),
                    text = stringResource(Res.string.lib_kotlin),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Monospace,
                        color = Color.Black,
                    ),
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .fillMaxWidth(),
                    text = stringResource(Res.string.lib_kotlin_coroutines),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Monospace,
                        color = Color.Black,
                    ),
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .fillMaxWidth(),
                    text = stringResource(Res.string.lib_compose),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Monospace,
                        color = Color.Black,
                    ),
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .fillMaxWidth(),
                    text = stringResource(Res.string.lib_decompose),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Monospace,
                        color = Color.Black,
                    ),
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .fillMaxWidth(),
                    text = stringResource(Res.string.lib_mvikotlin),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Monospace,
                        color = Color.Black,
                    ),
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .fillMaxWidth(),
                    text = stringResource(Res.string.lib_sqldelight),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Monospace,
                        color = Color.Black,
                    ),
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .fillMaxWidth(),
                    text = stringResource(Res.string.lib_rw_settings),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Monospace,
                        color = Color.Black,
                    ),
                )
                Spacer(modifier = Modifier.weight(1F))
                ClickableText(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 6.dp, bottom = 12.dp)
                        .fillMaxWidth(),
                    text = buildAnnotatedString {
                        append(stringResource(Res.string.project_label))
                    },
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Monospace,
                        color = Color.Black,
                        textDecoration = TextDecoration.Underline
                    ),
                    onClick = { uriHandler.openUri(URL_PROJECT) },
                )
            }
        },
        modifier = modifier,
    )
}