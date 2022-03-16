package br.com.fiap.apifindbar.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalTime

@Document(collection = "bar")
data class BarModel(

    @Id
    val id: String? = null,
    val nome: String,
    val horarioAbertura: LocalTime,
    val horarioFechamento: LocalTime,
    val endereco: EnderecoModel,
    val tipo: TipoBarEnum,
    val estilosMusicais: List<EstiloMusicalEnum>,
    val musicaAoVivo: Boolean,
    var avaliacao: Double = 0.0,
    var comentarios: List<ComentarioModel>? = mutableListOf(),
    val tags: List<TagModel>? = mutableListOf(),

    )
