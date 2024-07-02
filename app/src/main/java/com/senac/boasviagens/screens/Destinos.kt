@file:OptIn(ExperimentalFoundationApi::class)

package com.senac.boasviagens.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.senac.boasviagens.R
import com.senac.boasviagens.components.MyTopBar
import com.senac.boasviagens.dataBase.AppDataBase
import com.senac.boasviagens.models.Destino
import com.senac.boasviagens.viewmodels.DestinoViewModel
import com.senac.boasviagens.viewmodels.DestinoViewModelFactory

fun dest(){

}

@Composable
fun Destinos() {


    val destinoViewModel: DestinoViewModel = viewModel(
        factory = DestinoViewModelFactory(AppDataBase.getDatabase(LocalContext.current))
    )

//    val list = listOf(
//        Destino(1, "Egito", "12/12/2022", "05/01/2023", 12585.50, "lazer"),
//        Destino(2, "França", "08/12/2021", "02/01/2022", 45398.45, "trabalho"),
//        Destino(3, "Suiça", "18/12/2020", "03/01/2021", 65524.25, "lazer")
//    )

    val destinosLista = destinoViewModel.getAll().collectAsState(initial = emptyList())

    val navController = rememberNavController()

    val ctx = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                    navController.navigate("viagem")
//                    Toast.makeText(
//                        ctx, "novo",
//                        Toast.LENGTH_SHORT
//                    ).show()
                }) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = ""
                )
            }
        }

    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {

            NavHost(
                navController = navController,
                startDestination = "dest"
            ) {
                composable("viagem") {
                    Viagens(
                        onBack = {navController.navigateUp()}
                    )
                }

                composable("dest") {
                    dest()
                }

                composable("viagem/{id}") {
                    Viagens(
                        onBack = {navController.navigateUp()}
                    )
                }

            }

            LazyColumn {
                items(items = destinosLista.value) {
                    DestinoCard(
                        p = it,
                        onDelete =  {
                            destinoViewModel.delet(it)
                        },
                        onEdit = {
                            navController.navigate("viagem")
                        }
                    )
                }
            }


        }
    }

}

@Composable
fun DestinoCard(p: Destino, onDelete: () -> Unit, onEdit: () -> Unit) {
    val ctx = LocalContext.current
    Card(elevation = CardDefaults.cardElevation(
        defaultElevation = 8.dp

    ),
        border = BorderStroke(1.dp, Color.Gray),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize()
            .combinedClickable(
                onClick = {
                    onEdit()
                },
                onLongClick = {
                    onDelete()
                }
            )


    ) {
        Column(modifier = Modifier.padding(4.dp)) {

            Row {

                if (p.finalidade == "lazer") {
                    Image(
                        painter = painterResource(id = R.drawable.lazer),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(55.dp)
                            .weight(0.8f)
                            .padding(top = 8.dp, end = 10.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.trabalho),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(55.dp)
                            .weight(0.8f)
                            .padding(top = 8.dp, end = 10.dp)
                            .clip(CircleShape)
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(3f)
                ) {
                    Row {
                        Text(
                            text = p.destino,
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 22.sp
                        )
                    }

                    Row {
                        Text(
                            text = "Inicio ${p.inicio} - Fim ${p.fim}",
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }

                    Row {
                        Text(
                            text = "Orçamento R$${p.valor}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDestinos() {
    Destinos()
}