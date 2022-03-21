package br.com.fiap.apifindbar.service.impl

import br.com.fiap.apifindbar.converter.BarConverterImpl
import br.com.fiap.apifindbar.converter.ComentarioConverter
import br.com.fiap.apifindbar.dto.ComentarioDTO
import br.com.fiap.apifindbar.exception.ComentarioNaoEncontradoException
import br.com.fiap.apifindbar.model.ComentarioModel
import br.com.fiap.apifindbar.repository.BarRepository
import br.com.fiap.apifindbar.repository.ComentarioRepository
import br.com.fiap.apifindbar.service.BarService
import br.com.fiap.apifindbar.service.ComentarioService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class ComentarioServiceImpl(
    private val comentarioConverter: ComentarioConverter,
    private val comentarioRepository: ComentarioRepository,
    ) : ComentarioService {


    override fun addComments(barId: String, comentario: ComentarioModel): ComentarioModel {
        comentario.barId = barId
        val com: ComentarioDTO = createOne(comentarioConverter.toDTO(comentario))
        return comentarioConverter.toModel(com)
    }

     override fun findOne(id: String): ComentarioDTO {
        return comentarioConverter.toDTO(findOneById(id))
    }

    private fun findOneById(id: String): ComentarioModel {
        return comentarioRepository.findByIdOrNull(id) ?: throw ComentarioNaoEncontradoException("Comentario nao encontrado!")
    }

    override fun deleteComment(id: String) {
        findOneById(id)
        comentarioRepository.deleteById(id)
    }

    override fun createOne(novoComentarioDTO: ComentarioDTO): ComentarioDTO {
        return comentarioConverter.toDTO(comentarioRepository.save(comentarioConverter.toModel(novoComentarioDTO)))
    }

    override fun updateComment(commentId: String, comentario: ComentarioDTO): ComentarioDTO {
        val com = findOneById(commentId);
        val comAlterado = ComentarioModel(
            id = commentId,
            mensagem = comentario.mensagem,
            nota = comentario.nota,
            likes = comentario.likes,
            dislikes = comentario.dislikes,
        )
        return comentarioConverter.toDTO(comentarioRepository.save(comAlterado))
    }
   }