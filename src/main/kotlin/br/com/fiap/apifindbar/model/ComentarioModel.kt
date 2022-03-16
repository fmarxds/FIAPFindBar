package br.com.fiap.apifindbar.model

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "comentarios")
data class ComentarioModel(

    @Field("id")
    var id: String? = null,
    val mensagem: String,
    val nota: Int,
    var likes: Int = 0,
    var dislikes: Int = 0,

)