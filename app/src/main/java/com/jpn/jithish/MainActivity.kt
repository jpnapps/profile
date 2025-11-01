package com.jpn.jithish

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.jpn.core.designsystem.theme.JithishTheme
import com.jpn.feature.appearance.presentation.viewmodel.MainActivityViewModel
import com.jpn.jithish.presentation.screen.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppRoot()
            /*  val currentTheme by getSelectedThemeUseCase().collectAsState(initial = AppThemeType.SUNNY)
              JithishTheme(themeType = currentTheme) {
                  MainScreen()
              }*/
        }
    }
}

@Composable
fun AppRoot(
    viewModel: MainActivityViewModel = hiltViewModel()
) {
    val selectedTheme by viewModel.selectedTheme.collectAsState()

    JithishTheme(themeType = selectedTheme) {
        MainScreen()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JithishTheme {
        Greeting("Android")
    }
}


/*  Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  Greeting(
                      name = "Android",
                      modifier = Modifier.padding(innerPadding)
                  )
                  MainScreen( )
              }*/