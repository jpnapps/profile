package com.jpn.feature.appearance.data.usecase

import com.jpn.core.designsystem.theme.AppThemeType
import com.jpn.feature.appearance.domain.repository.AppearanceRepository
import com.jpn.feature.appearance.domain.usecase.GetSelectedThemeUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectedThemeUseCaseImpl @Inject constructor(
    private val repository: AppearanceRepository
): GetSelectedThemeUseCase {
    override operator fun invoke(): Flow<AppThemeType> = repository.getSelectedTheme()
}
