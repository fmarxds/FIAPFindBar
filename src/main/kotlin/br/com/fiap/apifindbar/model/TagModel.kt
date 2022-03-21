package br.com.fiap.apifindbar.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "tags")
data class TagModel(

    @Id
    var id: String? = null,
    var barId: String,
    val value: String,

)
