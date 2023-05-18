package dev.t1r.themebuilder.component.export.integration

import dev.t1r.themebuilder.component.export.ExportComponent
import dev.t1r.themebuilder.component.export.store.ExportStore

internal val stateToModel: ExportStore.State.() -> ExportComponent.Model =
    {
        ExportComponent.Model(
            composeThemeExportString = composeThemeExportString,
            androidXmlExportString = androidXmlExportString,
            isShareShowing = isCanShare,
        )
    }