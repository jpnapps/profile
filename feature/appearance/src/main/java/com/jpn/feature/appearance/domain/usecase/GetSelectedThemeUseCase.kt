package com.jpn.feature.appearance.domain.usecase

import com.jpn.core.designsystem.theme.AppThemeType
import kotlinx.coroutines.flow.Flow

interface GetSelectedThemeUseCase {
    operator fun invoke(): Flow<AppThemeType>
}