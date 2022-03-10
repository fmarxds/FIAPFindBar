package br.com.fiap.apifindbar.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalTime

@Document(collection = "bar")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BarModel(

    @Id
    val id: String? = null,
    val nome: String,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val horarioAbertura: LocalTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    val horarioFechamento: LocalTime,

    val endereco: EnderecoModel,
    val tipo: TipoBarEnum,
    val estilosMusicais: List<EstiloMusicalEnum>,
    val musicaAoVivo: Boolean,
    var avaliacao: Double = 0.0,
    val comentarios: List<ComentarioModel>,
    val tags: List<TagModel>,

)
