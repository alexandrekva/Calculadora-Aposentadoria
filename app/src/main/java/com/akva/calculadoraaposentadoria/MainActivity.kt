package com.akva.calculadoraaposentadoria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.akva.calculadoraaposentadoria.navigation.Screens
import com.akva.calculadoraaposentadoria.navigation.SetupNavGraph
import com.akva.calculadoraaposentadoria.ui.theme.CalculadoraAposentadoriaTheme


class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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