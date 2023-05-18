package dev.t1r.themebuilder.data.platform

import dev.t1r.themebuilder.entity.platform.Os
import dev.t1r.themebuilder.repository.platform.PlatformRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PlatformRepositoryImpl(
    private val shareAction: ((String) -> Unit)?,
) : PlatformRepository {
    override fun platform(): Flow<Os> = flowOf(currentOs())

    override fun share(text: String) {
        shareAction?.invoke(text)
    }
}