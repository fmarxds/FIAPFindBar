package br.com.fiap.apifindbar.service.impl

import br.com.fiap.apifindbar.converter.BarConverterImpl
import br.com.fiap.apifindbar.converter.TagConverter
import br.com.fiap.apifindbar.dto.TagDTO
import br.com.fiap.apifindbar.exception.TagNaoEncontradoException
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
    private val tagRepository: TagRepository,
    ) : TagService {

    override fun findAll(): TagDTO {
        TODO("Not yet implemented")
    }


    //   override fun findAll(): TagDTO {
//        var tags1 = tagRepository.findAll()
//        return tagConverter.toDTO(tags1)
//  }


    override fun addTag(barId: String, tag: TagModel): TagModel {
        //val bar: BarDTO = barService.findOne(barId!!)

        //bar.comentarios?.add(createOne(comentario))
        //barRepository.save(barConverter.toModel(bar))
        tag.id = barId
        val com: TagDTO = createOne(tagConverter.toDTO(tag))
        return tagConverter.toModel(com)
    }

    override fun findOne(id: String): TagDTO {
        return tagConverter.toDTO(findOneById(id))
    }

    private fun findOneById(id: String): TagModel {
        return tagRepository.findByIdOrNull(id) ?: throw TagNaoEncontradoException("Tag nao encontrado!")
    }

    override fun deleteTag(id: String) {
        findOneById(id)
        tagRepository.deleteById(id)
    }

    override fun createOne(novoTagDTO: TagDTO): TagDTO {
        return tagConverter.toDTO(tagRepository.save(tagConverter.toModel(novoTagDTO)))
    }

    override fun updateTag(tagId: String, tag: TagDTO): TagDTO {
        val com = findOneById(tagId);
        val comAlterado = TagModel(
            id = tagId,
            value = tag.value,
        )
        return tagConverter.toDTO(tagRepository.save(comAlterado))
    }
   }