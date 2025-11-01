package com.jpn.domain.usecase

import com.jpn.domain.model.MoreMenuItem

interface GetMoreMenuItemsUseCase {
    suspend operator fun invoke(): List<MoreMenuItem>
}