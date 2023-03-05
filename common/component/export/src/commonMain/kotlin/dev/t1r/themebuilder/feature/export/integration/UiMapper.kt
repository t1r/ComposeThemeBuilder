package dev.t1r.themebuilder.feature.export.integration

import dev.t1r.themebuilder.feature.export.ExportComponent
import dev.t1r.themebuilder.feature.export.store.ExportStore

internal val stateToModel: ExportStore.State.() -> ExportComponent.Model =
    {
        ExportComponent.Model(
            composeThemeExportString = composeThemeExportString,
            androidXmlExportString = androidXmlExportString,
        )
    }