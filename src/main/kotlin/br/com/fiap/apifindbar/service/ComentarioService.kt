package br.com.fiap.apifindbar.service

import br.com.fiap.apifindbar.dto.ComentarioDTO
import br.com.fiap.apifindbar.model.ComentarioModel


interface ComentarioService {

    fun addComments(comentario: ComentarioModel): ComentarioModel

    fun findOne(id: String): ComentarioDTO

    fun deleteComment (id: String)

    fun createOne(novoComentarioDTO: ComentarioDTO): ComentarioDTO
}
