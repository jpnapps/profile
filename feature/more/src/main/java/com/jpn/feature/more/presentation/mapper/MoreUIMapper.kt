package com.jpn.feature.more.presentation.mapper

import com.jpn.domain.model.MoreMenuItem
import com.jpn.feature.more.presentation.model.MoreMenuItemUI

fun MoreMenuItem.toUiModel() =when(this) {
    MoreMenuItem.Pwd->MoreMenuItemUI.Pwd
    MoreMenuItem.Settings->MoreMenuItemUI.Settings
    MoreMenuItem.Study->MoreMenuItemUI.Study
    MoreMenuItem.Vault->MoreMenuItemUI.Vault
    MoreMenuItem.Theme->MoreMenuItemUI.Theme
    MoreMenuItem.Help->MoreMenuItemUI.Help
    else-> MoreMenuItemUI.Logout
}