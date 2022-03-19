package br.com.fiap.apifindbar.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalTime

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BarAlteracaoDTO(

    val nome: String? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val horarioAbertura: LocalTime? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val horarioFechamento: LocalTime? = null,

    val endereco: EnderecoAlteracaoDTO? = null,

    val tipo: TipoBarEnumDTO? = null,

    val estilosMusicais: List<EstiloMusicalEnumDTO>? = null,

    val musicaAoVivo: Boolean? = null,

)
