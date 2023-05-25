import org.jetbrains.compose.web.dom.Text
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow("ComposeThemeBuilder") {
            // TODO
            Text("HELLO WORLD!")
        }
    }
}
