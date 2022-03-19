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
    private val barRepository: BarRepository,
    private val barService: BarService,
    private val barConverter: BarConverterImpl,
    private val comentarioConverter: ComentarioConverter,
    private val comentarioRepository: ComentarioRepository,
    ) : ComentarioService {


    override fun addComments(comentario: ComentarioModel): ComentarioModel {
        //val bar: BarDTO = barService.findOne(barId!!)

        //bar.comentarios?.add(createOne(comentario))
        //barRepository.save(barConverter.toModel(bar))
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