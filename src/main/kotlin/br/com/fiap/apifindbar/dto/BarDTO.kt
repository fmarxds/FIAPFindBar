package br.com.fiap.apifindbar.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalTime

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BarDTO(

    val id: String? = null,

    val nome: String,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val horarioAbertura: LocalTime,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val horarioFechamento: LocalTime,

    val endereco: EnderecoDTO,

    val tipo: TipoBarEnumDTO,

    val estilosMusicais: List<EstiloMusicalEnumDTO>,

    val musicaAoVivo: Boolean,

    var avaliacao: Double = 0.0,

    var comentarios: MutableList<ComentarioDTO>?,

    var tags: List<TagDTO>?,

)
