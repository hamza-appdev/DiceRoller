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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun playernamescreen(
    onNavigationtodicegame:(String, String)-> Unit
) {
    var player1 by remember { mutableStateOf("") }
    var player2 by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier= Modifier.padding(40.dp))

        Image(painter = painterResource(R.drawable.dice_logo), contentDescription = "Dice game img",
        )

        Spacer(modifier= Modifier.padding(25.dp))

        Text("Enter Names of players",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier= Modifier.padding(10.dp))

        OutlinedTextField(
            value = player1,
            onValueChange = {
               if (it.length<=8){
                   player1=it
               }
            },
            label = {Text("player1")},
            modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
            singleLine = true
        )
        Spacer(modifier = Modifier.padding(4.dp))
        OutlinedTextField(
            value = player2,
            onValueChange = {
                if (it.length<=8){
                    player2=it
                }
            },
            label = {Text("player2")},
            modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
            singleLine = true
        )
        Spacer(modifier= Modifier.padding(15.dp))

        Button(onClick = {
            onNavigationtodicegame(player1,player2)
        },
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            enabled = player1.isNotBlank() &&  player2.isNotBlank()  && player1 != player2
        ) {
            Text("Start Game")
        }







    }
}