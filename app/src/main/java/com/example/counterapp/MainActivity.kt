package com.example.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.counterapp.ui.theme.CounterAppTheme
import androidx.compose.runtime.remember as remember1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterAppTheme {
                myCounter()

            }
        }
    }
}
@Composable
fun myCounter() {
    var count by remember1 { mutableIntStateOf(0) }
    var userInput by remember1 { mutableStateOf("150") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                strokeWidth = 8.dp,
                trackColor = Color.Transparent,
                progress = count.toFloat() / userInput.toFloat(),
            )

            Text(
                text = "$count",
                modifier = Modifier
                    .align(Alignment.Center)
                    .graphicsLayer(translationY =  (-4).dp.value)
            )
        }

        Row(modifier = Modifier) {
            Button(onClick = {
                if (count < userInput.toInt()) {
                    count++
                }
            },
                shape = RoundedCornerShape(10.dp)) {
                Text(text = "+")
            }
            Spacer(modifier = Modifier.size(15.dp))
            Button(onClick = { count-- },
                shape = RoundedCornerShape(10.dp)) {
                Text(text = "-")
            }
        }

        TextField(
            value = userInput,
            onValueChange = {
                userInput = it
                // Optionally update the progress value here based on the user input
            },
            label = { Text("Enter Max Value") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(onClick = {
            // Reset button action
            count = 0
            userInput="150"
        },
            shape = RoundedCornerShape(10.dp)) {
            Text(text = "Reset")
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CounterPreview() {
    CounterAppTheme {
        myCounter()

    }
}