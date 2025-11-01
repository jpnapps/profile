package com.jpn.domain.model

sealed class MoreMenuItem {
    object Pwd : MoreMenuItem()
    object Settings : MoreMenuItem()
    object Study : MoreMenuItem()
    object Help : MoreMenuItem()
    object Vault : MoreMenuItem()
    object Theme : MoreMenuItem()
    object Logout : MoreMenuItem()
}