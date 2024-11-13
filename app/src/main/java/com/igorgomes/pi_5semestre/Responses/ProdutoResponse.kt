package com.igorgomes.pi_5semestre.Responses

data class ProdutoResponse(
    val id: Int,
    val nome: String,
    val preco: Double,
    var quantidade: Int
)

