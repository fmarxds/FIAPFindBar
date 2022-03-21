package br.com.fiap.apifindbar.service

import br.com.fiap.apifindbar.dto.BarDTO
import br.com.fiap.apifindbar.dto.ListaBarDTO
import br.com.fiap.apifindbar.dto.TagDTO
import br.com.fiap.apifindbar.model.TagModel


interface TagService {

    fun findAll(): TagDTO

    fun addTag(barId: String, tag: TagModel): TagModel

    fun findOne(id: String): TagDTO

    fun deleteTag (id: String)

    fun createOne(novoTagDTO: TagDTO): TagDTO

    fun updateTag(tagId: String, tag: TagDTO): TagDTO
}
