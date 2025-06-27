package com.jpn.feature.profile.presentation.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jpn.feature.profile.presentation.viewmodel.ProfileUiState
import com.jpn.feature.profile.presentation.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val uiState = viewModel.uiState.collectAsState()
        when (val state=uiState.value) {
            is ProfileUiState.Loading -> {
                LoadingScreen("Fetching profile...")
            }

            is ProfileUiState.Success -> {
                val profile = state.profile
                profile.let {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(profile.name, style = MaterialTheme.typography.titleLarge)
                        Text(profile.jobTitle, style = MaterialTheme.typography.titleMedium)
                        Text("Email: ${profile.email}", style = MaterialTheme.typography.labelSmall)
                        Text("Phone: ${profile.phone}", style = MaterialTheme.typography.labelSmall)
                        Text(
                            "Location: ${profile.location}",
                            style = MaterialTheme.typography.labelSmall
                        )
                        //Text("Experience: ${profile.experience}",style = MaterialTheme.typography.labelSmall)

                        Spacer(modifier = Modifier.height(16.dp))
                        Text("SUMMARY", style = MaterialTheme.typography.titleSmall)

                        Text(profile.summary, style = MaterialTheme.typography.bodySmall)

                        Spacer(modifier = Modifier.height(16.dp))
                        Text("SKILLS", style = MaterialTheme.typography.titleSmall)

                        Text(profile.skills, style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.height(64.dp))
                        Button(onClick = { /*onEditClick(profile)*/ }) {
                            Text("Edit Profile")
                        }
                    }
                }
                //Text("My Name: Jithish ", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text("About Me: Android babe with Compose charm ")
            }

            is ProfileUiState.Error -> {
                val error = (uiState as ProfileUiState.Error).message
                Text(text = error, color = Color.Red)
            }
        }
        //val profile= viewModel.profile

    }
}

@Composable
fun LoadingScreen(message: String = "Loading...") {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(message, style = MaterialTheme.typography.bodyMedium)
        }
    }
}