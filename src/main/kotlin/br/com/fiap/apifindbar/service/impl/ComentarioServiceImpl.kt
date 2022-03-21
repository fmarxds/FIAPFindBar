package br.com.fiap.apifindbar.service.impl

import br.com.fiap.apifindbar.converter.ComentarioConverter
import br.com.fiap.apifindbar.dto.*
import br.com.fiap.apifindbar.exception.ComentarioNaoEncontradoException
import br.com.fiap.apifindbar.model.ComentarioModel
import br.com.fiap.apifindbar.repository.ComentarioRepository
import br.com.fiap.apifindbar.service.ComentarioService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class ComentarioServiceImpl(
    private val comentarioConverter: ComentarioConverter,
    private val comentarioRepository: ComentarioRepository,
) : ComentarioService {

    override fun addComments(barId: String, comentario: ComentarioNovoDTO): ComentarioDTO {

        val novoComentario = ComentarioModel(
            barId = barId,
            mensagem = comentario.mensagem,
            nota = comentario.nota,
        )

        return comentarioConverter.toDTO(comentarioRepository.save(novoComentario))

    }

    override fun findOne(id: String): ComentarioDTO {
        return comentarioConverter.toDTO(findOneById(id))
    }

    override fun findAllComentarioModelByBarId(barId: String): Collection<ComentarioModel> {
        return comentarioRepository.findAllByBarId(barId)
    }

    override fun deleteComment(id: String) {
        findOneById(id)
        comentarioRepository.deleteById(id)
    }

    override fun updateComment(commentId: String, comentario: ComentarioAlteracaoDTO): ComentarioDTO {
        val comentarioOriginal = findOneById(commentId)

        val comentarioAlterado = ComentarioModel(
            id = comentarioOriginal.id,
            barId = comentarioOriginal.barId,
            mensagem = comentario.mensagem ?: comentarioOriginal.mensagem,
            nota = comentario.nota ?: comentarioOriginal.nota,
            likes = comentarioOriginal.likes,
            dislikes = comentarioOriginal.dislikes,
        )

        return comentarioConverter.toDTO(comentarioRepository.save(comentarioAlterado))
    }

    override fun addReaction(id:String, comentarioReactionDTO: ComentarioReactionDTO): ComentarioDTO {

        val comentario = findOneById(id)

        if (ComentarioReactionEnumDTO.LIKE == comentarioReactionDTO.reaction) {
            comentario.likes += 1
        } else {
            comentario.dislikes += 1
        }

        return comentarioConverter.toDTO(comentarioRepository.save(comentario))

    }

    private fun findOneById(id: String): ComentarioModel {
        return comentarioRepository.findByIdOrNull(id)
            ?: throw ComentarioNaoEncontradoException("Comentario nao encontrado!")
    }

}