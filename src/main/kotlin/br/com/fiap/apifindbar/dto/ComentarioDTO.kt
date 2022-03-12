package br.com.fiap.apifindbar.dto

data class ComentarioDTO(

    var id: String? = null,
    val mensagem: String,
    val nota: Int,
    var likes: Int = 0,
    var dislikes: Int = 0,

)
