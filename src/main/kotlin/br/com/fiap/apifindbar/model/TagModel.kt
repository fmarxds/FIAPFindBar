package br.com.fiap.apifindbar.model

import org.springframework.data.mongodb.core.mapping.Field

data class TagModel(

    @Field("id")
    var id: String? = null,
    val value: String,

)
