package com.jpn.feature.pwd.presentation.navigation

sealed class PwdScreen(val route: String) {
    object List : PwdScreen("pwd/list")
    object Add : PwdScreen("pwd/add")
/*    object Edit : PwdScreen("pwd/edit")
    object Detail : PwdScreen("pwd/detail")*/
    object Edit : PwdScreen("pwd/edit/{itemId}") {
        fun createRoute(itemId: Int) = "pwd/edit/$itemId"
        const val ARG_ITEM_ID = "itemId"
    }

    object Detail : PwdScreen("pwd/detail/{itemId}") {
        fun createRoute(itemId: Int) = "pwd/detail/$itemId"
        const val ARG_ITEM_ID = "itemId"
    }
}