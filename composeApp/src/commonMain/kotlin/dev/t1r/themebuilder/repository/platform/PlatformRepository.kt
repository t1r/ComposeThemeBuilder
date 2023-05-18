package dev.t1r.themebuilder.repository.platform

import dev.t1r.themebuilder.entity.platform.Os
import kotlinx.coroutines.flow.Flow

interface PlatformRepository {
    fun platform(): Flow<Os>
    fun share(text: String)
}