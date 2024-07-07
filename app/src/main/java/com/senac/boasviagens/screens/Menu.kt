package com.senac.boasviagens.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.senac.boasviagens.R
import com.senac.boasviagens.components.MyTopBar
import com.senac.boasviagens.dataBase.AppDataBase
import com.senac.boasviagens.models.Dados
import com.senac.boasviagens.viewmodels.DadosViewModel
import com.senac.boasviagens.viewmodels.DadosViewModelFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope

private fun isSelected(currentDestination: NavDestination?, route: String): Boolean {
    return currentDestination?.hierarchy?.any { it.route == route } == true
}

@Composable
fun Home(id: String) {

    Scaffold(
        topBar = {
            MyTopBar("App Boa Viagem") {System.exit(1)}
        }
    ) {

        val db = AppDataBase.getDatabase(LocalContext.current)

        val dadosViewModel: DadosViewModel = viewModel(
            factory = DadosViewModelFactory(db)
        )

        LaunchedEffect(id) {
            if (id != null) {
                val user = dadosViewModel.findById(id.toLong())
                user?.let { dadosViewModel.setUiState(it) }
            }
        }

        val state = dadosViewModel.uiState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)

        ) {

            Row {

                Text(
                    text = "Bem vindo " + state.value.login,
                    fontSize = 22.sp
                )

            }

        }
    }
}

@Composable
fun Menu(id: String) {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.value?.destination

            BottomNavigation {

                BottomNavigationItem(
                    selected = isSelected(currentDestination, "home"),
                    onClick = { navController.navigate("home") },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = "",
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                )

                BottomNavigationItem(
                    selected = isSelected(currentDestination, "destinos"),
                    onClick = { navController.navigate("destinos") },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.descolar),
                            contentDescription = "",
                            modifier = Modifier
                                .size(55.dp)
                        )
                    }
                )

                BottomNavigationItem(
                    selected = isSelected(currentDestination, "sobre"),
                    onClick = { navController.navigate("sobre") },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.informacoes),
                            contentDescription = "",
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)

        ) {
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") {

                    Home(id)
                }
                composable("destinos") {
                    Destinos()
                }

                composable("sobre") {

                    Sobre()
                }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenu() {
    Menu("")
}