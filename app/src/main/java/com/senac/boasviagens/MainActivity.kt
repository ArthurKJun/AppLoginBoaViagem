package com.senac.boasviagens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.senac.boasviagens.ui.theme.BoasViagensTheme
import com.senac.boasviagens.screens.telaLogin
import com.senac.boasviagens.screens.cadUsuario


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoasViagensTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}


@SuppressLint("SuspiciousIndentation")
@Composable
fun MyApp(){

    val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "telaLogin"
            ){
                composable("telaLogin"){

                    telaLogin (onCadUsuario = {
                        navController.navigate("cadUsuario")
                    })
                }

                composable("cadUsuario"){
                    cadUsuario(onBack = {navController.navigateUp()})
                }

            }


        }




