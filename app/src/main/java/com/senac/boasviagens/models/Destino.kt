package com.senac.boasviagens.models

data class Destino (
    val id: Int=0,
    val destino: String = "",
    val inicio: String = "",
    val fim: String = "",
    val valor: Double
){//data = não preciso escrever os getters e setters
}