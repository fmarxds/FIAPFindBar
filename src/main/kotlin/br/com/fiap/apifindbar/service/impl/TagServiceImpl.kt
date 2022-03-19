package br.com.fiap.apifindbar.service.impl

import br.com.fiap.apifindbar.converter.BarConverterImpl
import br.com.fiap.apifindbar.converter.TagConverter
import br.com.fiap.apifindbar.dto.BarDTO
import br.com.fiap.apifindbar.dto.TagDTO
import br.com.fiap.apifindbar.exception.TagNaoEncontradaException
import br.com.fiap.apifindbar.model.TagModel
import br.com.fiap.apifindbar.repository.BarRepository
import br.com.fiap.apifindbar.repository.TagRepository
import br.com.fiap.apifindbar.service.BarService
import br.com.fiap.apifindbar.service.TagService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class TagServiceImpl(
    private val barRepository: BarRepository,
    private val barService: BarService,
    private val barConverter: BarConverterImpl,
    private val tagConverter: TagConverter,
    private val tagRepository: TagRepository
): TagService {
    override fun addTag(barId: String, tag: TagDTO): TagDTO {
        val bar: BarDTO = barService.findOne(barId)
        bar.tags?.add(tag)
        barRepository.save(barConverter.toModel(bar))
        return tag
    }


    override fun findOne(id: String): TagDTO {
        return tagConverter.toDTO(findOneById(id))
    }

    private fun findOneById(id: String): TagModel{
        return tagRepository.findByIdOrNull(id) ?: throw TagNaoEncontradaException("tag nao encontrada!")
    }

}