package com.senac.boasviagens.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senac.boasviagens.models.Destino


@Composable
fun Destinos(){

    val list = listOf(
        Destino(1, "Egito", "12/12/2022", "05/01/2023", 12585.50),
        Destino(2, "França", "08/12/2021", "02/01/2022", 45398.45),
        Destino(3, "Suiça", "18/12/2020", "03/01/2021", 65524.25)
    )
    val ctx = LocalContext.current
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = {
                Toast.makeText(ctx, "novo",
                    Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    imageVector = Icons.Default.AddCircle ,
                    contentDescription = ""
                )
            }
        }
    ){
        Column(modifier = Modifier.padding(it)) {
            LazyColumn() {
                items(items = list) {
                    DestinoCard(p = it)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DestinoCard(p: Destino){
    val ctx = LocalContext.current
    Card(elevation = CardDefaults.cardElevation(
        defaultElevation = 8.dp
    ),
        border = BorderStroke(1.dp, Color.Gray),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize()
            .clickable {
                Toast
                    .makeText(
                        ctx, "Produto: ${p.destino}",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            Row {
                Text(
                    text = p.destino,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 22.sp,
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
                    style = MaterialTheme.typography.bodySmall)
            }

        }
    }
}
