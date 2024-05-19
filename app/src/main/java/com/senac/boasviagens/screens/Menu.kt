package com.senac.boasviagens.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

private fun isSelected(currentDestination: NavDestination?, route:String): Boolean {
    return currentDestination?.hierarchy?.any {it.route == route} == true
}


@Composable
fun Home() {
    Text(text = "teste")
}

@Composable
fun Menu(onBack: () ->Unit){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            Home ()
        }
        composable("login"){
            telaLogin(onCadUsuario = { /*TODO*/ }, onLogin = { /*TODO*/ })
        }
    }

Scaffold (
    bottomBar = {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.value?.destination

        BottomNavigation {

            BottomNavigationItem(
                selected = isSelected(currentDestination, "login"),
                onClick = { navController.navigate("login") },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = ""
                    )
                }
            )

            BottomNavigationItem(
                selected = isSelected(currentDestination, "menu"),
                onClick = { navController.navigate("menu") },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = ""
                    )
                }
            )

            BottomNavigationItem(
                selected = isSelected(currentDestination, "menu"),
                onClick = { navController.navigate("menu") },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = ""
                    )
                }
            )
        }
    }
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(it)

    ){

    }
}
}

@Preview(showBackground = true)
@Composable
fun PreviewMenu() {
    Menu({})
}