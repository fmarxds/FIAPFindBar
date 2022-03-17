package br.com.fiap.apifindbar.service.impl

import br.com.fiap.apifindbar.converter.BarConverterImpl
import br.com.fiap.apifindbar.converter.ComentarioConverter
import br.com.fiap.apifindbar.dto.BarDTO
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


    override fun addComments(barId: String, comentario: ComentarioDTO): ComentarioDTO {
        val bar: BarDTO = barService.findOne(barId!!)
        bar.comentarios?.add(comentario)
        barRepository.save(barConverter.toModel(bar))
        return comentario
    }

     override fun findOne(id: String): ComentarioDTO {
        return comentarioConverter.toDTO(findOneById(id))
    }

    private fun findOneById(id: String): ComentarioModel? {
        return comentarioRepository.findByIdOrNull(id) ?: throw ComentarioNaoEncontradoException("Comentario nao encontrado!")
    }

    override fun deleteComment(id: String) {
        findOneById(id)
        comentarioRepository.deleteById(id)
    }
 }