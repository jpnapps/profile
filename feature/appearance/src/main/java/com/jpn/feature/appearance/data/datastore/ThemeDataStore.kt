package com.jpn.feature.appearance.data.datastore

/*
private val Context.themeDataStore by preferencesDataStore("theme_preferences")

class ThemeDataStoreManager(private val context: Context) {

    companion object {
        private val SELECTED_THEME_KEY = stringPreferencesKey("selected_theme")
        private const val DEFAULT_THEME = "LIGHT"
    }

    val selectedTheme: Flow<String> = context.themeDataStore.data.map { prefs ->
        prefs[SELECTED_THEME_KEY] ?: DEFAULT_THEME
    }

    suspend fun saveTheme(theme: String) {
        context.themeDataStore.edit { prefs ->
            prefs[SELECTED_THEME_KEY] = theme
        }
    }
}
*/
