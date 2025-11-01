package com.jpn.feature.more.domain

import com.jpn.domain.model.MoreMenuItem
import com.jpn.domain.repository.MoreRepository
import com.jpn.domain.usecase.GetMoreMenuItemsUseCase
import javax.inject.Inject

class GetMoreMenuItemsUseCaseImpl @Inject constructor(
    private val repository: MoreRepository
) : GetMoreMenuItemsUseCase {
    override suspend fun invoke(): List<MoreMenuItem> {
        // For now, static items. In future, can be from DB/API.
        return listOf(
            MoreMenuItem.Pwd,
            MoreMenuItem.Settings,
            MoreMenuItem.Study,
            MoreMenuItem.Vault,
            MoreMenuItem.Theme,
            MoreMenuItem.Logout,
        )
    }
}
