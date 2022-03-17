package br.com.fiap.apifindbar.service

import br.com.fiap.apifindbar.dto.ComentarioDTO


interface ComentarioService {

    fun addComments(barId: String, comentario: ComentarioDTO): ComentarioDTO

    fun findOne(id: String): ComentarioDTO

    fun deleteComment (id: String)
}
