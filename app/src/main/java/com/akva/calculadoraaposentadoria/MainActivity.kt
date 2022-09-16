package com.akva.calculadoraaposentadoria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.akva.calculadoraaposentadoria.navigation.Screens
import com.akva.calculadoraaposentadoria.navigation.SetupNavGraph
import com.akva.calculadoraaposentadoria.ui.theme.CalculadoraAposentadoriaTheme


class MainActivity : ComponentActivity() {
    @RequiresApi(33)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            CalculadoraAposentadoriaTheme {
                Surface {
                    SetupNavGraph(
                        navController = rememberNavController(),
                        startDestination = Screens.InputScreen.route
                    )
                }
            }
        }
    }
}