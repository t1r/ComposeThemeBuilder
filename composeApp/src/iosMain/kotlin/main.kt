import androidx.compose.ui.window.ComposeUIViewController
import dev.t1r.themebuilder.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController { App() }
}
