package com.example.diceroller.Navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.diceroller.screens.Dicegamescreen
import com.example.diceroller.screens.Winscreen
import com.example.diceroller.screens.playernamescreen
import com.example.diceroller.screens.splashscreen

@SuppressLint("SuspiciousIndentation")
@Composable
fun screennavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

   var game= "Dicegamescreen"
   var win = "Winscreen"
   var playername= "playernamescreen"

    NavHost(navController=navController, startDestination = "splashscreen") {
        composable("splashscreen"){
            splashscreen(
                {
                    navController.navigate(playername){
                        popUpTo("splashscreen") { inclusive = true } // remove splash from backstack
                    } })
        }
        composable(playername) {
            playernamescreen(
                onNavigationtodicegame = {p1,p2->
                    navController.navigate("$game/$p1/$p2")
                })
        }
        composable("$game/{player1}/{player2}") {backStackEntry-> //it store arguments of nevigations
            var player1 = backStackEntry.arguments?.getString("player1") ?:""
            var player2 = backStackEntry.arguments?.getString("player2") ?:""

            Dicegamescreen(
                player1 = player1,
                player2 = player2,

                // For Win
                onWin = { winnerName ->
                    navController.navigate("$win/$winnerName")
                },

                AgainNavigateToPlayersName = {
                    navController.popBackStack(playername,false)
                })
        }

        composable("$win/{winnerName}") {backStackEntry->
            val winnerName = backStackEntry.arguments?.getString("winnerName")
            Winscreen(
                winnerName =winnerName,
                onPlayAgain ={
                    navController.popBackStack(playername,false)
                })
        }
    }


}