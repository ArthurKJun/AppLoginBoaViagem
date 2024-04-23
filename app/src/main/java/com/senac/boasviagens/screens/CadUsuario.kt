package com.senac.boasviagens.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun cadUsuario(onBack: ()->Unit){//retorno do botao para voltar a main

    Column (
        modifier = Modifier
            .fillMaxSize()
    ){

        Button(onClick = { onBack() }) {
            Text(text = "Registrar")
        }

        Text(
            text = "Cadastro de Usuário",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}