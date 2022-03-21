package br.com.fiap.apifindbar.service

import br.com.fiap.apifindbar.dto.TagAlteracaoDTO
import br.com.fiap.apifindbar.dto.TagDTO
import br.com.fiap.apifindbar.dto.TagNovoDTO
import br.com.fiap.apifindbar.model.ComentarioModel
import br.com.fiap.apifindbar.model.TagModel


interface TagService {

    fun findAll(): Collection<TagDTO>

    fun addTag(barId: String, tag: TagNovoDTO): TagDTO

    fun findOne(id: String): TagDTO

    fun findAllTagModelByBarId(barId: String): Collection<TagModel>

    fun deleteTag(id: String)

    fun updateTag(tagId: String, tag: TagAlteracaoDTO): TagDTO
}
