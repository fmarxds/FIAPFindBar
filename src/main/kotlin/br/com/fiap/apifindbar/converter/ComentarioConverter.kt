package br.com.fiap.apifindbar.converter

import br.com.fiap.apifindbar.dto.ComentarioDTO
import br.com.fiap.apifindbar.model.ComentarioModel
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ComentarioConverter {
    fun toModel(comentarioDTO: ComentarioDTO): ComentarioModel

    fun toDTO(comentarioModel: ComentarioModel): ComentarioDTO

}