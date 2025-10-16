package com.example.diceroller.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceroller.R

@Composable
fun Winscreen(
    winnerName: String?,
    onPlayAgain: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier= Modifier.padding(40.dp))
        Image(painter = painterResource(R.drawable.winner_trophy), contentDescription = "winner trophy",
            modifier= Modifier.size(350.dp)
        )

        Spacer(modifier= Modifier.padding(6.dp))
        Text(
            "Congratulations!",
            fontSize = 30.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier= Modifier.padding(6.dp))
        Text(
            "${winnerName} Won The Game",
            fontSize = 19.sp,
            fontFamily = FontFamily.Monospace,

        )

        Spacer(modifier= Modifier.padding(26.dp))
        Button(onClick = {
            onPlayAgain()
        },
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text("Play Again")
        }

}
}