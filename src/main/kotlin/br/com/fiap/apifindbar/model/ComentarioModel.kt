package br.com.fiap.apifindbar.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "comentarios")
data class ComentarioModel(

    @Id
    var id: String? = null,
    var barId: String,
    val mensagem: String,
    val nota: Int,
    var likes: Int = 0,
    var dislikes: Int = 0,

)
