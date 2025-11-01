package com.jpn.feature.appearance.data.usecase

import com.jpn.core.designsystem.theme.AppThemeType
import com.jpn.feature.appearance.domain.repository.AppearanceRepository
import com.jpn.feature.appearance.domain.usecase.SaveThemeUseCase
import javax.inject.Inject

class SaveThemeUseCaseImpl @Inject constructor(
    private val repository: AppearanceRepository
) : SaveThemeUseCase {
    override suspend operator fun invoke(theme: AppThemeType) {
        repository.saveTheme(theme)
    }
}
