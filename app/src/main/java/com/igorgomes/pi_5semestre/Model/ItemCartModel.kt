package com.igorgomes.pi_5semestre.Model

import java.io.Serializable

data class ItemCartModel(
    val idItem: Int,
    val nome: String,
    val preco: Double,
    var quantidade: Int
) : Serializable


