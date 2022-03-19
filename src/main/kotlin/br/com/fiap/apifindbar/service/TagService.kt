package br.com.fiap.apifindbar.service

import br.com.fiap.apifindbar.dto.ComentarioDTO
import br.com.fiap.apifindbar.dto.TagDTO

interface TagService {

    fun addTag(barId: String, tag: TagDTO): TagDTO

    fun findOne(id: String): TagDTO
}