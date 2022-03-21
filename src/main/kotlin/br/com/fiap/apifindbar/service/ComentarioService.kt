package br.com.fiap.apifindbar.service

import br.com.fiap.apifindbar.dto.ComentarioAlteracaoDTO
import br.com.fiap.apifindbar.dto.ComentarioDTO
import br.com.fiap.apifindbar.dto.ComentarioNovoDTO
import br.com.fiap.apifindbar.dto.ComentarioReactionDTO
import br.com.fiap.apifindbar.model.ComentarioModel


interface ComentarioService {

    fun addComments(barId: String, comentario: ComentarioNovoDTO): ComentarioDTO

    fun findOne(id: String): ComentarioDTO

    fun findAllComentarioModelByBarId(barId: String): Collection<ComentarioModel>

    fun deleteComment (id: String)

    fun updateComment(commentId: String, comentario: ComentarioAlteracaoDTO): ComentarioDTO

    fun addReaction(id: String, comentarioReactionDTO: ComentarioReactionDTO): ComentarioDTO

}
