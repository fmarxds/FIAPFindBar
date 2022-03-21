package br.com.fiap.apifindbar.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ComentarioAlteracaoDTO(

    val mensagem: String? = null,
    val nota: Int? = null,

)
