package com.jpn.jithish

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jpn.jithish.presentation.screen.MainScreen
import com.jpn.jithish.ui.theme.JithishTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JithishTheme {
                MainScreen( )

            }
        }
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