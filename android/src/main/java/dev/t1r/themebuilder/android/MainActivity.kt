package dev.t1r.themebuilder.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.arkivanov.decompose.defaultComponentContext
import dev.t1r.themebuilder.feature.root.integration.RootComponentImpl
import dev.t1r.themebuilder.ui.compose.DefaultAppTheme
import dev.t1r.themebuilder.ui.compose.RootContent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultAppTheme {
                RootContent(
                    RootComponentImpl(defaultComponentContext())
                )
            }
        }
    }
}