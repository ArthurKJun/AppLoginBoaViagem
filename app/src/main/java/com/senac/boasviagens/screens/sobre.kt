package com.senac.boasviagens.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Sobre(){
    Column (
        modifier = Modifier

    ){
        Text(text = "Sobre o Desenvolvedor")
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSobre(){
    Sobre()
}