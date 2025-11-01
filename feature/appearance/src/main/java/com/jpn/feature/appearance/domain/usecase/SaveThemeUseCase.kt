package com.jpn.feature.appearance.domain.usecase

import com.jpn.core.designsystem.theme.AppThemeType

interface SaveThemeUseCase {
    suspend operator fun invoke(theme: AppThemeType)
}