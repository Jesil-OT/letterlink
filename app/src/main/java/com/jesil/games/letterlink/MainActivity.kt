package com.jesil.games.letterlink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jesil.games.letterlink.game.screen.GameScreen
import com.jesil.games.letterlink.home.screen.HomeScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView)

        if (windowInsetsController != null) {
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())

            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "home_screen",
            ) {
                composable("home_screen") { HomeScreen(navController) }
                composable(
                    "game_screen",
                    enterTransition = {
                        slideInVertically(
                            initialOffsetY = { it },
                            animationSpec = tween(500)
                        )
                    },
                    exitTransition = {
                        slideOutVertically(
                            targetOffsetY = { it },
                            animationSpec = tween(500)
                        )
                    },
                    popEnterTransition = {
                        slideInVertically(
                            initialOffsetY = { it },
                            animationSpec = tween(500)
                        )
                    },
                    popExitTransition = {
                        slideOutVertically(
                            targetOffsetY = { it },
                            animationSpec = tween(500)
                        )
                    }) { GameScreen() }
            }
        }
    }
}