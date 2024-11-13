package com.igorgomes.pi_5semestre.Responses

data class LoginResponse(
    val usuarioId: Int,
    val usuarioNome: String,
    val usuarioEmail: String,
    val usuarioSenha: String
)
