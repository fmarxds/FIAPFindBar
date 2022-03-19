package br.com.fiap.apifindbar.converter

import br.com.fiap.apifindbar.dto.TagDTO
import br.com.fiap.apifindbar.model.TagModel
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface TagConverter {

    fun toModel(tagDTO: TagDTO): TagModel

    fun toDTO(tagModel: TagModel?): TagDTO
}