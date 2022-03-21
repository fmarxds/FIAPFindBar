package br.com.fiap.apifindbar.service.impl

import br.com.fiap.apifindbar.converter.TagConverter
import br.com.fiap.apifindbar.dto.TagAlteracaoDTO
import br.com.fiap.apifindbar.dto.TagDTO
import br.com.fiap.apifindbar.dto.TagNovoDTO
import br.com.fiap.apifindbar.exception.TagNaoEncontradoException
import br.com.fiap.apifindbar.model.TagModel
import br.com.fiap.apifindbar.repository.TagRepository
import br.com.fiap.apifindbar.service.TagService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class TagServiceImpl(
    private val tagConverter: TagConverter,
    private val tagRepository: TagRepository,
) : TagService {

    override fun findAll(): Collection<TagDTO> {
        return tagConverter.toDTO(tagRepository.findAll())
    }

    override fun addTag(barId: String, tag: TagNovoDTO): TagDTO {

        val novoTag = TagModel(
            barId = barId,
            value = tag.value,
        )

        return tagConverter.toDTO(tagRepository.save(novoTag))

    }

    override fun findOne(id: String): TagDTO {
        return tagConverter.toDTO(findOneById(id))
    }

    override fun findAllTagModelByBarId(barId: String): Collection<TagModel> {
        return tagRepository.findAllByBarId(barId)
    }

    override fun deleteTag(id: String) {
        findOneById(id)
        tagRepository.deleteById(id)
    }

    override fun updateTag(tagId: String, tag: TagAlteracaoDTO): TagDTO {
        val tagOriginal = findOneById(tagId);

        val tagAlterado = TagModel(
            id = tagOriginal.id,
            barId = tagOriginal.barId,
            value = tag.value ?: tagOriginal.value
        )

        return tagConverter.toDTO(tagRepository.save(tagAlterado))
    }

    private fun findOneById(id: String): TagModel {
        return tagRepository.findByIdOrNull(id) ?: throw TagNaoEncontradoException("Tag nao encontrado!")
    }

}