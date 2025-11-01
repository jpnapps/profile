package com.jpn.core.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*@Composable
fun HealthCard(
    title: String,
    currentValue: Int,
    unit: String,
    goal: Int,
    step: Int,
    max: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    lightColor: Color,
    darkColor: Color
) {
    // Calculate progress from 0f to 1f
    val progress = (currentValue.toFloat() / goal.toFloat()).coerceIn(0f, 1f)
    // Blend gradient depending on progress
    val gradientColors = listOf(
        lightColor, // Always start with light at top
        ColorUtils.blendARGB(lightColor.toArgb(), darkColor.toArgb(), progress).let { Color(it) },
        darkColor // Darker at bottom, more dominant as progress grows
    )

    Card(
        modifier = modifier
            .size(160.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(gradientColors)
                )
                .fillMaxSize()
                .drawWithContent {
                    // Clip gradient according to progress
                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(lightColor, darkColor),
                            startY = 0f,
                            endY = size.height
                        ),
                        size = Size(width = size.width, height = size.height * progress),
                        topLeft = Offset(0f, size.height * (1 - progress))
                    )
                    drawContent()
                }
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Title
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )

                // Current value
                Text(
                    text = "$currentValue $unit",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )

                // Goal
                Text(
                    text = "Goal: $goal $unit",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )

                // Bottom buttons (+ / -)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = {
                            if (currentValue - step >= 0) onValueChange(currentValue - step)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Decrease",
                            tint = Color.White
                        )
                    }

                    IconButton(
                        onClick = {
                            if (currentValue + step <= max) onValueChange(currentValue + step)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Increase",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}*/



@Composable
fun StepGoalCard(
    title: String,
    currentValue: Int,
    unit: String,
    goal: Int,
    step: Int,
    max: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    lightColor: Color,
    darkColor: Color
) {
    //val filledSteps = (currentValue / step).coerceAtMost(totalSteps)
    val fillFraction = (currentValue.toFloat() / goal.toFloat()).coerceIn(0f, 1f)

   // Log.d("jpLogs","step: "+step+" totalSteps: "+totalSteps+" currentValue: "+currentValue+" filledSteps: "+filledSteps+ " fillFraction: "+fillFraction)

    Card(
        modifier = modifier
            .size(160.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background showing step-based progress
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(lightColor)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fillFraction) // fill proportionally from bottom
                    .align(Alignment.BottomStart)
                    .background(darkColor)
            )

            // Card content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "$currentValue/$goal $unit",
                    style = MaterialTheme.typography.headlineSmall,
                )
                /*Text(
                    text = "Goal: $goal/$unit",
                    style = MaterialTheme.typography.bodyMedium,
                )*/
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { if (currentValue - step >= 0) onValueChange(currentValue - step) }
                    ) { Icon(Icons.Default.Remove, contentDescription = "Decrease") }

                    IconButton(
                        onClick = { if (currentValue + step <= max) onValueChange(currentValue + step) }
                    ) { Icon(Icons.Default.Add, contentDescription = "Increase") }
                }
            }
        }
    }
}
