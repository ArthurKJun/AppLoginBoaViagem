package com.senac.boasviagens.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.senac.boasviagens.R
import com.senac.boasviagens.components.MyTopBar

private fun isSelected(currentDestination: NavDestination?, route:String): Boolean {
    return currentDestination?.hierarchy?.any {it.route == route} == true
}

@Composable
fun Home() {

    Scaffold (
        topBar = {
            MyTopBar("Home")
        }
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)

        ){
            Text(text = "home")
        }
    }
}

@Composable
fun Menu(){

    val navController = rememberNavController()

Scaffold (
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
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(it)

    ){
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                Home()
            }
            composable("destinos"){
                Destinos()
            }

            composable("sobre"){
                Sobre()
            }


        }
    }
}
}

@Preview(showBackground = true)
@Composable
fun PreviewMenu() {
    Menu()
}