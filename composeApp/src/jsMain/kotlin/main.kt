import dev.t1r.themebuilder.App
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow("ComposeThemeBuilder") {
            App()
        }
    }
}
