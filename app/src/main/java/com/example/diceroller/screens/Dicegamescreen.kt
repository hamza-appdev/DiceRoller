package com.example.diceroller.screens

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceroller.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dicegamescreen(
    player1: String,
    player2: String,
    AgainNavigateToPlayersName:()-> Unit,
    onWin:(String)-> Unit

) {
    Scaffold(containerColor = Color.White,
       topBar = {
           TopAppBar(
               title = {Text("Doce Rolling Game")}, actions = {
                   Button(onClick = { AgainNavigateToPlayersName()},
                       modifier = Modifier.padding(16.dp),
                       colors = ButtonDefaults.buttonColors(
                           containerColor = Color.Black,
                           contentColor = Color.White)
                       ) {
                       Text("New Game")
                   }
               },
               colors = TopAppBarDefaults.topAppBarColors(Color.White)
           )
       }
    ) {innerpadding->
        DiceGame(
            innerpadding,
            player1 = player1,
            player2=player2,
            onWin = {winner->
                onWin(winner)
            }
            )
    }
}


@SuppressLint("SuspiciousIndentation")
@Composable
fun DiceGame(
    innerpadding: PaddingValues,
    player1: String,
    player2: String,
    onWin:(String)-> Unit

) {
    //for player score
    var player1Score by remember { mutableStateOf(0) }
    var player2Score  by remember { mutableStateOf(0) }
    //for dice rolling img
    var dicevalue by remember { mutableIntStateOf(1) }
    // for buttton
    var isRolling by remember { mutableStateOf(false) }
    var isPlayer1Turn by remember { mutableStateOf(true) }

    //for corountine scope
    var scope = rememberCoroutineScope()

    //for animation
    var rotation = remember { Animatable(0f) }
    /*
    Ye ek state object hai jo rotation value (degrees) hold karta hai —
    aur jab aap rotation.animateTo() call karte ho,
    to Compose us composable ko automatically animate karta hai jahan ye value use ho rahi ho.
    * */



    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
            .padding(innerpadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(20.dp))
        Text("Let's Play",
           style = TextStyle(
               fontSize = 25.sp,
               fontFamily = FontFamily.Monospace,
               fontWeight = FontWeight.Bold
           )
            )
        Spacer(modifier = Modifier.padding(10.dp))

        Box(modifier = Modifier.fillMaxWidth()
            .background(Color.Black)
            .height(40.dp)
            ,
            contentAlignment = Alignment.Center
            ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text("$player1 Score:$player1Score",color = Color.White, fontSize = 16.sp)
                Text("$player2 Score:$player2Score",color = Color.White, fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.padding(60.dp))
        Box(
            modifier = Modifier.padding(16.dp)
                .rotate(rotation.value)
            ,
            contentAlignment =Alignment.Center
        ){
            when (dicevalue) {
                1 -> scoregameimage1()
                2 -> scoregameimage2()
                3 -> scoregameimage3()
                4 -> scoregameimage4()
                5 -> scoregameimage5()
                else -> scoregameimage6()
            }
        }

        Spacer(modifier = Modifier.padding(40.dp))
        Row {
            Button(
                onClick = {
                    if (!isRolling){
                        isRolling=true
                        scope.launch {
                            //for animation
                            repeat(5){
                               dicevalue= (1..6).random()
                                rotation.snapTo(0f)
                                rotation.animateTo(180f,tween (durationMillis = 130))
                                delay(100)
                            }

                            dicevalue = (1..6).random()
                            player1Score += dicevalue

                            isRolling = false
                            isPlayer1Turn = false

                            if (dicevalue==6){
                                isPlayer1Turn=true
                            }

                            if (player1Score>=50){
                                onWin(player1)
                                return@launch //for stop coroutine code
                            }
                        }

                    }
                },
                modifier = Modifier.weight(1f),
                enabled = isPlayer1Turn && !isRolling,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text("P1:Roll Dice", color = Color.White)
            }
            Spacer(Modifier.padding(4.dp))
            Button(
                onClick = {
                    if (!isRolling){
                        isRolling=true
                        scope.launch {

                            //for animation
                            repeat(5){
                                dicevalue= (1..6).random()
                                rotation.snapTo(0f)
                                rotation.animateTo(180f,tween (durationMillis = 130))
                                delay(100)
                            }

                            dicevalue=(1..6).random()
                            player2Score +=dicevalue

                            isRolling=false
                          isPlayer1Turn=true
                            if (dicevalue==6){
                                isPlayer1Turn=false
                            }

                            if (player2Score>=50){
                                onWin(player2)
                                return@launch //for stop coroutine code
                            }


                        }


                    }
                },
                modifier = Modifier.weight(1f),
                enabled = !isPlayer1Turn && !isRolling,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text("P2:Roll Dice", color = Color.White)
            }
        }

    }














}