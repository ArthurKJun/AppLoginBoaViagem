package com.senac.boasviagens.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun Sobre(){
    Column (

        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()

    ){
        Row{
            Text(
                textAlign = TextAlign.Center,
                text = "Desenvolvido e Criado Por",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row{
            Text(
                textAlign = TextAlign.Center,
                text = "ARTHUR KLUG JUNIOR",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row{
            Text(
                textAlign = TextAlign.Center,
                    text = "Para a Matéria de Desenvolvimento Mobile",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row{
            Text(
                textAlign = TextAlign.Center,
                text = "Para contatar o Desenvolvedor mande um e-mail para",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row{
            Text(
                textAlign = TextAlign.Center,
                text = "artklug@gmail.com",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSobre(){
    Sobre()
}