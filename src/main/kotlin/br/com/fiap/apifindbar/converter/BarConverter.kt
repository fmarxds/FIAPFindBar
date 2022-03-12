package br.com.fiap.apifindbar.converter

import br.com.fiap.apifindbar.dto.BarDTO
import br.com.fiap.apifindbar.dto.BarNovoDTO
import br.com.fiap.apifindbar.model.BarModel
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BarConverter {

    fun toDTO(barModel: BarModel): BarDTO

    fun toDTO(barCollectionModel: Collection<BarModel>): Collection<BarDTO>

    fun toModel(barDTO: BarDTO): BarModel

    fun toModel(barCollectionDTO: Collection<BarDTO>): Collection<BarModel>

    fun toModel(barNovoDTO: BarNovoDTO): BarModel

}